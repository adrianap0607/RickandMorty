package com.example.laboratorio9.presentation.login
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio9.data.model.Character
import com.example.laboratorio9.data.model.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import com.example.laboratorio9.presentation.room.CharacterDao
import com.example.laboratorio9.presentation.room.LocationDao
import com.example.laboratorio9.presentation.room.CharacterEntity
import com.example.laboratorio9.presentation.room.LocationEntity

class LoginViewModel(
    private val userPrefs: DataStoreUserPrefs
): ViewModel() {

    // Guardar el nombre de usuario
    fun saveUserName(name: String) {
        viewModelScope.launch {
            userPrefs.setName(name)
        }
    }

    // Obtener el nombre de usuario guardado
    val userName: Flow<String?> = userPrefs.getName()

    // Función de sincronización de datos
    fun syncData(
        characters: List<Character>, // Recibe lista de Character
        locations: List<Location>,   // Recibe lista de Location
        characterDao: CharacterDao,
        locationDao: LocationDao,
        onSyncComplete: () -> Unit
    ) {
        viewModelScope.launch {
            delay(2000)


            val characterEntities = characters.map { character ->
                CharacterEntity(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    gender = character.gender
                )
            }


            val locationEntities = locations.map { location ->
                LocationEntity(
                    id = location.id,
                    name = location.name,
                    type = location.type,
                    dimension = location.dimension
                )
            }


            characterDao.insertAll(characterEntities)
            locationDao.insertAll(locationEntities)

            onSyncComplete()
        }
    }
}
