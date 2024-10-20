package com.example.laboratorio9.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio9.data.source.CharacterDb
import com.example.laboratorio9.data.source.LocationDb
import com.example.laboratorio9.presentation.login.DataStoreUserPrefs
import com.example.laboratorio9.presentation.login.LoginDestination
import com.example.laboratorio9.presentation.login.loginScreen
import com.example.laboratorio9.presentation.mainFlow.mainNavigationGraph
import com.example.laboratorio9.presentation.mainFlow.navigateToMainGraph
import com.example.laboratorio9.presentation.room.CharacterDao
import com.example.laboratorio9.presentation.room.LocationDao


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    userPrefs: DataStoreUserPrefs,
    characterDao: CharacterDao,
    locationDao: LocationDao,
    characterDb: CharacterDb,
    locationDb: LocationDb

) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination,
        modifier = modifier
    ) {
        loginScreen(
            onLoginClick = {
                navController.navigateToMainGraph(
                    navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                        inclusive = true
                    ).build()
                )
            },
            userPrefs = userPrefs,
            characterDao = characterDao,
            locationDao = locationDao,
            characterDb = characterDb,
            locationDb = locationDb
        )
        mainNavigationGraph(
            onLogOutClick = {
                navController.navigate(LoginDestination) {
                    popUpTo(0)
                }
            },
            characterDao = characterDao,
            locationDao = locationDao,
            userPrefs = userPrefs
        )
    }
}