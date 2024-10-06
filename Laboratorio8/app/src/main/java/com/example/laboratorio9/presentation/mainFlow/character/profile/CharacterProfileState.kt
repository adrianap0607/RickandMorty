package com.example.laboratorio9.presentation.mainFlow.character.profile

import com.example.laboratorio9.data.model.Character

data class CharacterProfileState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val data: List<Character> = emptyList()
)