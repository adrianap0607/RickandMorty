package com.example.laboratorio9.presentation.mainFlow.location.profile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio9.data.model.Location
import com.example.laboratorio9.data.source.LocationDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LocationProfileViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(LocationProfileState(isLoading = true))
    val uiState: StateFlow<LocationProfileState> = _uiState

    private val locationDb = LocationDb()


    fun getLocationData() {
        viewModelScope.launch {

            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            // Simulación de carga de datos con un retraso de 2 segundos
            delay(2000)


            val location = locationDb.getAllLocations().firstOrNull()

            // Actualizar el estado con la ubicación obtenida y finalizar la carga
            _uiState.update { state ->
                state.copy(
                    data = location,
                    isLoading = false
                )
            }
        }
    }


     // Función que se llama cuando se hace clic en la pantalla de carga.

    fun onLoadingClick() {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                hasError = true
            )
        }
    }

    //Función para volver a intentar la carga del perfil después de un error.
    fun retryLoading() {
        _uiState.update { state ->
            state.copy(
                isLoading = true,
                hasError = false
            )
        }
        getLocationData() // Reintentar la carga de datos
    }
}

