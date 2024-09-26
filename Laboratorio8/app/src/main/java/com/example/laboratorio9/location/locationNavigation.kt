package com.example.laboratorio9.location

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object locationDestination

fun NavGraphBuilder.locationScreen(
    onLocationClick: (Int) -> Unit
){
    composable<locationDestination> {
        locationRoute(
            onLocationClick = onLocationClick
        )
    }
}