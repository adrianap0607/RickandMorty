package com.example.laboratorio9.presentation.mainFlow.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.laboratorio9.presentation.login.DataStoreUserPrefs
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(
    onLogOutClick: () -> Unit,
    userPrefs: DataStoreUserPrefs
) {
    composable<ProfileDestination> {
        ProfileRoute(
            onLogOutClick = onLogOutClick,
            userPrefs= userPrefs)
    }
}