package com.example.laboratorio9.presentation.mainFlow.character.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio9.data.model.Character
import com.example.laboratorio9.data.source.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CharacterProfileViewModel : ViewModel() {

    // Estado interno del ViewModel utilizando MutableStateFlow
    private val _characterProfileState = MutableStateFlow(CharacterProfileState(isLoading = true))
    val characterProfileState: StateFlow<CharacterProfileState> = _characterProfileState

    private val characterDb = CharacterDb() // Instancia de la base de datos de personajes

    //Función para cargar el perfil de un personaje según su ID.

    fun getCharacterProfile(id: Int) {
        viewModelScope.launch {
            // Simulación de una carga de datos
            delay(2000)

            // Obtiene el personaje según el ID
            val character = characterDb.getCharacterById(id)

            // Actualiza el estado con el personaje obtenido y desactiva el estado de carga
            _characterProfileState.update { state ->
                state.copy(
                    data = listOf(character),
                    isLoading = false
                )
            }
        }
    }

    //Función para volver a intentar la carga del perfil

    fun retryLoading(id: Int) {
        _characterProfileState.update { state ->
            state.copy(isLoading = true, hasError = false)
        }
        getCharacterProfile(id)
    }


    fun onLoadingClick() {
        _characterProfileState.update { state ->
            state.copy(
                isLoading = false,
                hasError = true
            )
        }
    }
}
