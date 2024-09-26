package com.example.laboratorio9.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(
    onLogoutClick: () -> Unit
){
    composable<ProfileDestination> {
        profileRoute(onLogoutClick = onLogoutClick)
    }
}