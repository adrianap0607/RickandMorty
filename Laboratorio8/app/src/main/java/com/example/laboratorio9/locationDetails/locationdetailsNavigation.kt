package com.example.laboratorio9.locationDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class locationinfoDestination(val locationId: Int)

fun NavController.navigateToLocationInfoScreen(
    destination: locationinfoDestination,
    navOptions: NavOptions? = null
){
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.locationInfoScreen(onBackClick: () -> Unit){
    composable<locationinfoDestination>{backStackEntry ->
        val destination: locationinfoDestination = backStackEntry.toRoute()
        LocationDetailRoute(locationId = destination.locationId, onBackClick = onBackClick)
    }
}