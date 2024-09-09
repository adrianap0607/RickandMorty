package com.example.laboratorio8.characterDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailDestination(
    val characterId: Int
)

fun NavController.navigateToCharacterDetail(
    characterId: Int,
    navOptions: NavOptions? = null
) {

    this.navigate("characterDetail/$characterId", navOptions)
}

fun NavGraphBuilder.characterDetailScreen(onNavigateBack: () -> Unit) {
    composable("characterDetail/{characterId}") { backStackEntry ->

        val characterId = backStackEntry.arguments?.getString("characterId")?.toInt() ?: return@composable


        CharacterDetailScreen(
            characterId = characterId,
            onBackClick = onNavigateBack
        )
    }
}
