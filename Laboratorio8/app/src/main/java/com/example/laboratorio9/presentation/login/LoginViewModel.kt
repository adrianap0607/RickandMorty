package com.example.laboratorio9.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import com.example.laboratorio9.data.model.Character
import com.example.laboratorio9.data.model.Location
import com.example.laboratorio9.presentation.login.DataStoreUserPrefs
import com.example.laboratorio9.presentation.room.CharacterDao
import com.example.laboratorio9.presentation.room.LocationDao
import com.example.laboratorio9.presentation.room.toEntity


class LoginViewModel(
    private val userPrefs: DataStoreUserPrefs
): ViewModel() {

    // Guardar el nombre del usuario en DataStore
    fun saveUserName(name: String) {
        viewModelScope.launch {
            userPrefs.setName(name)
        }
    }

    // Obtener el nombre del usuario desde DataStore
    val userName: Flow<String?> = userPrefs.getName()

    // Sincronización de datos iniciales a Room
    fun syncData(
        characters: List<Character>,  // Lista de personajes desde CharacterDb
        locations: List<Location>,    // Lista de ubicaciones desde LocationDb
        characterDao: CharacterDao,   // DAO de personajes para Room
        locationDao: LocationDao,     // DAO de ubicaciones para Room
        onSyncComplete: () -> Unit    // Callback cuando la sincronización termina
    ) {
        viewModelScope.launch {
            delay(2000)  // Simular tiempo de sincronización (2 segundos)
            characterDao.insertAll(characters.map { it.toEntity() }) // Insertar personajes en Room
            locationDao.insertAll(locations.map { it.toEntity() })   // Insertar ubicaciones en Room
            onSyncComplete() // Llamar callback cuando termina
        }
    }
}
