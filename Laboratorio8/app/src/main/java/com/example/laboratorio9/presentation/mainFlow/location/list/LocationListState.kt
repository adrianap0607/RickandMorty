package com.example.laboratorio9.presentation.mainFlow.location.list


import com.example.laboratorio9.data.model.Location

data class LocationListState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val data: List<Location> = emptyList()
)