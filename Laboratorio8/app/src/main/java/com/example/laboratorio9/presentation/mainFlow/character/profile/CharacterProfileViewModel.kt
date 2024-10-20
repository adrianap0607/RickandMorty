package com.example.laboratorio9.presentation.mainFlow.character.profile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio9.presentation.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterProfileViewModel(
    private val characterRepository: CharacterRepository // Inyección del repositorio
) : ViewModel() {

    // Estado interno del ViewModel utilizando MutableStateFlow
    private val _characterProfileState = MutableStateFlow(CharacterProfileState(isLoading = true))
    val characterProfileState: StateFlow<CharacterProfileState> = _characterProfileState

    // Función para cargar el perfil de un personaje según su ID.
    fun getCharacterProfile(id: Int) {
        viewModelScope.launch {
            // Intentamos obtener el personaje por su ID desde el repositorio
            characterRepository.getCharacterById(id).collect { characterEntity ->
                // Actualiza el estado con el personaje obtenido y desactiva el estado de carga
                _characterProfileState.update { state ->
                    state.copy(
                        data = listOfNotNull(characterEntity), // Evita null, usa listOfNotNull
                        isLoading = false
                    )
                }
            }
        }
    }

    // Función para volver a intentar la carga del perfil
    fun retryLoading(id: Int) {
        _characterProfileState.update { state ->
            state.copy(isLoading = true, hasError = false)
        }
        getCharacterProfile(id)
    }

    // Función que se llama cuando se hace clic en la pantalla de carga
    fun onLoadingClick() {
        _characterProfileState.update { state ->
            state.copy(
                isLoading = false,
                hasError = true
            )
        }
    }
}

