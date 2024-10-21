package com.example.laboratorio9.presentation.mainFlow.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio9.data.source.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CharacterListViewModel : ViewModel() {


    private val _characterState = MutableStateFlow(CharacterState(isLoading = true))
    val characterState: StateFlow<CharacterState> = _characterState

    private val characterDb = CharacterDb()

    init {
        // Inicia la carga de personajes al inicializar el ViewModel
        loadCharacters()
    }


    private fun loadCharacters() {
        viewModelScope.launch {
            // Simulación de una carga de datos con retraso de 4 segundos
            delay(4000)

            // Obtiene la lista de personajes de la base de datos
            val characters = characterDb.getAllCharacters()

            // Actualiza el estado con la lista de personajes y desactiva el estado de carga
            _characterState.update { state ->
                state.copy(
                    data = characters,
                    isLoading = false
                )
            }
        }
    }

    //Función para volver a intentar la carga de personajes después de un error
    fun retryLoading() {
        _characterState.update { state ->
            state.copy(isLoading = true, hasError = false)
        }
        loadCharacters()
    }

    //Función que se llama cuando se hace clic en la pantalla de carga.

    fun onLoadingClick() {
        _characterState.update { state ->
            state.copy(
                isLoading = false,
                hasError = true
            )
        }
    }
}
