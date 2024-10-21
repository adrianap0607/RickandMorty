package com.example.laboratorio9.presentation.mainFlow.location.list


import com.example.laboratorio9.data.model.Location

import com.example.laboratorio9.presentation.room.LocationEntity


data class LocationListState(
    val data: List<Location> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)
