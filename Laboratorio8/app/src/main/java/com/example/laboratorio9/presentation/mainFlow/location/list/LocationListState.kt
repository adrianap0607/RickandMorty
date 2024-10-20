package com.example.laboratorio9.presentation.mainFlow.location.list


import com.example.laboratorio9.data.model.Location

import com.example.laboratorio9.presentation.room.LocationEntity

// Cambia el tipo de lista a tu modelo de ubicación
data class LocationListState(
    val data: List<Location> = emptyList(), // Usa tu clase aquí
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)
