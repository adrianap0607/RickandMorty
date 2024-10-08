package com.example.laboratorio8.characters

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CharacterDestination

fun NavGraphBuilder.characterNavigation(
    onCharacterClick: (Int) -> Unit,
    onBackToLogin: () -> Unit
) {
    composable("characters") {
        CharactersScreen(onCharacterClick = onCharacterClick)

        BackHandler {

            onBackToLogin()
        }
    }
}
