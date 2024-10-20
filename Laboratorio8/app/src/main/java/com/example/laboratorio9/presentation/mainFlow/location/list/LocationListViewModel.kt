package com.example.laboratorio9.presentation.mainFlow.location.list

import androidx.lifecycle.ViewModel


import androidx.lifecycle.viewModelScope
import com.example.laboratorio9.data.model.Location
import com.example.laboratorio9.data.source.LocationDb
import com.example.laboratorio9.presentation.room.LocationDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LocationListViewModel(private val locationDao: LocationDao) : ViewModel() {
    private val _locationListState = MutableStateFlow(LocationListState(isLoading = true))
    val locationListState: StateFlow<LocationListState> = _locationListState

    private val locationDb = LocationDb()


    fun loadLocations() {
        viewModelScope.launch {
            // Simulación de una carga de datos con retraso de 4 segundos
            delay(4000)

            // Obtiene la lista de ubicaciones de la base de datos
            val locations = locationDb.getAllLocations()


            _locationListState.update { state ->
                state.copy(
                    data = locations,
                    isLoading = false
                )
            }
        }
    }


    fun retryLoading() {
        _locationListState.update { state ->
            state.copy(isLoading = true, hasError = false)
        }
        loadLocations()
    }


    fun onLoadingClick() {
        _locationListState.update { state ->
            state.copy(
                isLoading = false,
                hasError = true
            )
        }
    }
}
