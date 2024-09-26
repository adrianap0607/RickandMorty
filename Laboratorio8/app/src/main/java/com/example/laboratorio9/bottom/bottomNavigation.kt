package com.example.laboratorio9.bottom

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.laboratorio9.characters.CharactersScreen
import com.example.laboratorio9.location.LocationScreen
import com.example.laboratorio9.profile.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data class BottomNavDestination(val destination: String)

fun NavGraphBuilder.bottomNavigation(navController: NavController) {
    composable(BottomNavItem.Characters.route) {
        CharactersScreen(onCharacterClick = { characterId ->
            // Navegación con Serialización
            val destination = BottomNavDestination("characterDetail/$characterId")
            navController.navigate(destination.destination)
        })
    }

    composable(BottomNavItem.Locations.route) {
        LocationScreen()
    }

    composable(BottomNavItem.Profile.route) {
        val destination = BottomNavDestination("profile")
        ProfileScreen(onLogoutClick = {
            navController.navigate("login") {
                popUpTo("login") { inclusive = true } // Limpiar el backstack
            }
        })
    }
}
