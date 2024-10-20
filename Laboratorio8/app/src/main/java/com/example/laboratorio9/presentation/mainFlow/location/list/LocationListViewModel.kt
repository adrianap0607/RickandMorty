package com.example.laboratorio9.presentation.mainFlow.location.list
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio9.presentation.repository.LocationRepository
import com.example.laboratorio9.presentation.room.toLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel(
    private val locationRepository: LocationRepository // Inyección del repositorio
) : ViewModel() {

    // Estado interno del ViewModel utilizando MutableStateFlow
    private val _locationListState = MutableStateFlow(LocationListState(isLoading = true))
    val locationListState: StateFlow<LocationListState> = _locationListState

    init {
        loadLocations() // Cargar las ubicaciones al inicializar el ViewModel
    }

    // Función para cargar las ubicaciones desde el repositorio
    private fun loadLocations() {
        viewModelScope.launch {
            locationRepository.getAllLocations().collect { locationEntities ->
                // Convierte las entidades a modelos
                val locations = locationEntities.map { it.toLocation() }
                _locationListState.update { state ->
                    state.copy(
                        data = locations, // Actualiza el estado con la lista de modelos
                        isLoading = false
                    )
                }
            }
        }
    }

    // Función para volver a intentar la carga después de un error
    fun retryLoading() {
        _locationListState.update { state ->
            state.copy(isLoading = true, hasError = false)
        }
        loadLocations()
    }

    // Función que se llama cuando se hace clic en la pantalla de carga para simular un error
    fun onLoadingClick() {
        _locationListState.update { state ->
            state.copy(
                isLoading = false,
                hasError = true
            )
        }
    }
}
