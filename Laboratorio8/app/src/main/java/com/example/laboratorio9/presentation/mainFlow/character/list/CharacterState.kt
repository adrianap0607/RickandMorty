package com.example.laboratorio9.presentation.mainFlow.character.list

import com.example.laboratorio9.data.model.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val data: List<Character> = emptyList()
)