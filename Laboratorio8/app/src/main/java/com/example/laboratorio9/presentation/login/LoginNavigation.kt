package com.example.laboratorio9.presentation.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.laboratorio9.data.source.CharacterDb
import com.example.laboratorio9.data.source.LocationDb
import com.example.laboratorio9.presentation.room.CharacterDao
import com.example.laboratorio9.presentation.room.LocationDao
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: (String) -> Unit,
    userPrefs: DataStoreUserPrefs,
    characterDao: CharacterDao,
    locationDao: LocationDao,
    characterDb: CharacterDb,
    locationDb: LocationDb
) {
    composable<LoginDestination> {
        LoginRoute(
            onLoginClick = onLoginClick,
            userPrefs = userPrefs,
            characterDao = characterDao,
            locationDao = locationDao,
            characterDb = characterDb,
            locationDb = locationDb

        )
    }
}