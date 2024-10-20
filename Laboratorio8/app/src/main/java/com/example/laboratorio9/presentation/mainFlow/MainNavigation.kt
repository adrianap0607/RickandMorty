package com.example.laboratorio9.presentation.mainFlow

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio9.presentation.login.DataStoreUserPrefs
import com.example.laboratorio9.presentation.room.CharacterDao
import com.example.laboratorio9.presentation.room.LocationDao
import kotlinx.serialization.Serializable

@Serializable
data object MainNavigationGraph

fun NavController.navigateToMainGraph(navOptions: NavOptions? = null) {
    this.navigate(MainNavigationGraph, navOptions)
}

fun NavGraphBuilder.mainNavigationGraph(
    onLogOutClick: () -> Unit,
    characterDao: CharacterDao,
    locationDao: LocationDao,
    userPrefs: DataStoreUserPrefs
) {
    composable<MainNavigationGraph> {
        val nestedNavController = rememberNavController()
        MainFlowScreen(
            navController = nestedNavController,
            onLogOutClick = onLogOutClick,
            userPrefs = userPrefs,
            locationDao = locationDao
        )
    }
}
