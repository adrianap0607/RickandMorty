package com.example.laboratorio9.presentation.mainFlow.character.list
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.laboratorio9.presentation.room.CharacterDao
import kotlinx.serialization.Serializable

@Serializable
data object CharacterListDestination

fun NavGraphBuilder.characterListScreen(
    onCharacterClick: (Int) -> Unit,
    characterDao: CharacterDao // Pasamos el DAO aquí
) {
    composable<CharacterListDestination> {
        // Pasamos el DAO a la función CharacterListRoute
        CharacterListRoute(onCharacterClick = onCharacterClick, characterDao = characterDao)
    }
}
