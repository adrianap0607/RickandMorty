package com.example.laboratorio9.presentation.mainFlow.location.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio9.data.model.Location
import com.example.laboratorio9.data.source.LocationDb
import com.example.laboratorio9.presentation.mainFlow.ErrorScreen
import com.example.laboratorio9.presentation.mainFlow.LoadingScreen
import com.example.laboratorio9.presentation.room.LocationDao
import com.example.laboratorio9.ui.theme.laboratorio9Theme

@Composable
fun LocationListRoute(
    locationDao: LocationDao,
    onLocationClick: (Int) -> Unit,
    viewModel: LocationListViewModel = viewModel()
) {
    // Colecta el estado actual del ViewModel
    val locationListState by viewModel.locationListState.collectAsState()

    LocationListScreen(
        locationState = locationListState, // Usar LocationListState en lugar de LocationState
        onLocationClick = onLocationClick,
        onLoadingClick = { viewModel.onLoadingClick() },
        onRetry = { viewModel.retryLoading() },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun LocationListScreen(
    locationState: LocationListState, // Cambiar el tipo a LocationListState
    onLocationClick: (Int) -> Unit,
    onLoadingClick: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        locationState.isLoading -> {
            // Pantalla de carga
            LoadingScreen(modifier = modifier.clickable { onLoadingClick() })
        }
        locationState.hasError -> {
            // Pantalla de error con botón de reintentar
            ErrorScreen(
                errorMessage = "Error al obtener listado de ubicaciones. Intenta de nuevo.",
                onRetry = onRetry
            )
        }
        else -> {
            // Pantalla original de lista de ubicaciones
            LazyColumn(modifier = modifier) {
                items(locationState.data) { location ->
                    LocationItem(
                        location = location,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLocationClick(location.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun LocationItem(
    location: Location,
    modifier: Modifier = Modifier
) {
    val imageBackgroundColors = listOf(
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.inverseSurface
    )
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            color = imageBackgroundColors[(location.id % imageBackgroundColors.size)],
            shape = CircleShape
        ) {
            Box {
                Icon(
                    imageVector = Icons.Outlined.Place,
                    contentDescription = "Location Image",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Column {
            Text(text = location.name)
            Text(
                text = location.type,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLocationListScreen() {
    laboratorio9Theme {
        Surface {
            val db = LocationDb()
            LocationListScreen(
                locationState = LocationListState(data = db.getAllLocations().take(6)), // Usar LocationListState aquí
                onLocationClick = {},
                onLoadingClick = {},
                onRetry = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
