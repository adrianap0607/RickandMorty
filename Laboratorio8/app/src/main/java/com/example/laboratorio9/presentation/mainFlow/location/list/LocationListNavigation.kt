package com.example.laboratorio9.presentation.mainFlow.location.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.laboratorio9.presentation.room.LocationDao
import kotlinx.serialization.Serializable

@Serializable
data object LocationListDestination

fun NavGraphBuilder.locationListScreen(
    onLocationClick: (Int) -> Unit,
    locationDao: LocationDao,
) {
    composable<LocationListDestination> {
        LocationListRoute(
            locationDao = locationDao,
            onLocationClick = onLocationClick)
    }
}