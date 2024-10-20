package com.example.laboratorio9.presentation.mainFlow.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio9.presentation.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _characterState = MutableStateFlow(CharacterState(isLoading = true))
    val characterState: StateFlow<CharacterState> = _characterState

    init {

        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {

            characterRepository.getAllCharacters().collect { characters ->

                _characterState.update { state ->
                    state.copy(
                        data = characters,
                        isLoading = false
                    )
                }
            }
        }
    }


    fun retryLoading() {
        _characterState.update { state ->
            state.copy(isLoading = true, hasError = false)
        }
        loadCharacters()
    }


    fun onLoadingClick() {
        _characterState.update { state ->
            state.copy(
                isLoading = false,
                hasError = true
            )
        }
    }
}
