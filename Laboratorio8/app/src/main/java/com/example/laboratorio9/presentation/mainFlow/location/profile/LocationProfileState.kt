package com.example.laboratorio9.presentation.mainFlow.location.profile

import com.example.laboratorio9.data.model.Location


data class LocationProfileState(
    val data: Location? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)
