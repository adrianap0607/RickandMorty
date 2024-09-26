package com.example.laboratorio9.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.example.laboratorio9.profile.ProfileScreen

@Serializable
data class ProfileDestination(val userId: Int = 0) // Puedes agregar campos si necesitas

fun NavController.navigateToProfile(userId: Int = 0, navOptions: NavOptions? = null) {
    this.navigate("profile/$userId", navOptions)
}

fun NavGraphBuilder.profileNavigation() {
    composable("profile/{userId}") { backStackEntry ->
        val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 0
        ProfileScreen(userId = userId)
    }
}
