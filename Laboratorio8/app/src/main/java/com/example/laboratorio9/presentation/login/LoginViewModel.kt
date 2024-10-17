package com.example.laboratorio9.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.laboratorio9.presentation.login.DataStoreUserPrefs

class LoginViewModel(
    private val userPrefs: DataStoreUserPrefs

): ViewModel(){
    fun saveUserName (name: String){
        viewModelScope.launch {
            userPrefs.setName(name)
        }
    }
//se obtiene el nombre 
    val userName: Flow<String?> = userPrefs.getName()
}