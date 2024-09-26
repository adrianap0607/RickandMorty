package com.example.laboratorio9.location

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.laboratorio9.locationDetails.LocationDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailDestination(val locationId: Int)

fun NavController.navigateToLocationDetail(locationId: Int, navOptions: NavOptions? = null) {
    this.navigate("locationDetail/$locationId", navOptions)
}

fun NavGraphBuilder.locationNavigation(
    onLocationClick: (Int) -> Unit
) {
    composable("locations") {
        LocationScreen(onLocationClick = onLocationClick)
    }

    composable("locationDetail/{locationId}") { backStackEntry ->
        val locationId = backStackEntry.arguments?.getString("locationId")?.toInt() ?: return@composable

        LocationDetailScreen(locationId = locationId)
    }
}
