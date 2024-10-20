package com.example.laboratorio9.presentation.mainFlow.character.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.laboratorio9.presentation.repository.CharacterRepository

class CharacterProfileViewModelFactory(
    private val characterRepository: CharacterRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterProfileViewModel(characterRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
