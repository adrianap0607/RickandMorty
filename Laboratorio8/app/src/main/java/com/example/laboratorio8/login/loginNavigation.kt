package com.example.laboratorio8.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginNavigation(navController: NavController) {
    composable("login") {
        LoginScreen(
            onLoginClick = {
                // Navegación al listado de personajes
                navController.navigate("characters") {
                    popUpTo("login") { inclusive = true }
                }
            }
        )
    }
}
