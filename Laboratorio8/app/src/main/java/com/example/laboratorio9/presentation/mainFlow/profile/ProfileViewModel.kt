package com.example.laboratorio9.presentation.mainFlow.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.laboratorio9.presentation.login.DataStoreUserPrefs

class ProfileViewModel(
    private val userPrefs: DataStoreUserPrefs
) : ViewModel() {

    // Obtener el nombre del usuario desde DataStore
    val userName: Flow<String?> = userPrefs.getName()

    // Eliminar el nombre de DataStore (cerrar sesión)
    fun logOut() {
        viewModelScope.launch {
            userPrefs.clearName()
        }
    }
}
