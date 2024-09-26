package com.example.laboratorio9.characterDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class CharacterInfoDestination(val characterId: Int)

fun NavController.navigateToCharacterDetailsScreen(destination: CharacterInfoDestination, navOptions: NavOptions? = null){
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.characterInfoScreen(onBackClick: () -> Unit){
    composable<CharacterInfoDestination> { backStackEntry ->
        val destination: CharacterInfoDestination = backStackEntry.toRoute()
        CharacterDetailRoute(characterId = destination.characterId, onBackClick = onBackClick)

    }
}