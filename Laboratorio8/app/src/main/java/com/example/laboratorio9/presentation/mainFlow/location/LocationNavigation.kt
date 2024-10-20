package com.example.laboratorio9.presentation.mainFlow.location

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.example.laboratorio9.presentation.mainFlow.location.list.LocationListDestination
import com.example.laboratorio9.presentation.mainFlow.location.list.locationListScreen
import com.example.laboratorio9.presentation.mainFlow.location.profile.locationProfileScreen
import com.example.laboratorio9.presentation.mainFlow.location.profile.navigateToLocationProfileScreen
import com.example.laboratorio9.presentation.room.LocationDao
import kotlinx.serialization.Serializable

@Serializable
data object LocationsNavGraph

fun NavController.navigateToLocationsGraph(navOptions: NavOptions? = null) {
    this.navigate(LocationsNavGraph, navOptions)
}

fun NavGraphBuilder.locationsGraph(
    navController: NavController,
    locationDao: LocationDao
) {
    navigation<LocationsNavGraph>(
        startDestination = LocationListDestination
    ) {
        locationListScreen(
            locationDao = locationDao,
            onLocationClick = navController::navigateToLocationProfileScreen
        )
        locationProfileScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}
