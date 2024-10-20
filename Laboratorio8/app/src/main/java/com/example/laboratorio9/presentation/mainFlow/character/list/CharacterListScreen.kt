package com.example.laboratorio9.presentation.mainFlow.character.list
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio9.data.model.Character
import com.example.laboratorio9.data.source.CharacterDb
import com.example.laboratorio9.presentation.mainFlow.ErrorScreen
import com.example.laboratorio9.presentation.mainFlow.LoadingScreen
import com.example.laboratorio9.ui.theme.laboratorio9Theme

@Composable
fun CharacterListRoute(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharacterListViewModel = viewModel()
) {
    val characterState by viewModel.characterState.collectAsStateWithLifecycle()

    CharacterListScreen(
        characterState = characterState,
        onCharacterClick = onCharacterClick,
        onLoadingClick = { viewModel.onLoadingClick() },
        onRetry = { viewModel.retryLoading() },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun CharacterListScreen(
    characterState: CharacterState,
    onCharacterClick: (Int) -> Unit,
    onLoadingClick: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        characterState.isLoading -> {
            // Pantalla de carga
            LoadingScreen(modifier = modifier.clickable { onLoadingClick() })
        }
        characterState.hasError -> {
            // Pantalla de error con botón de reintentar
            ErrorScreen(
                errorMessage = "Error al obtener listado de personajes. Intenta de nuevo.",
                onRetry = onRetry
            )
        }
        else -> {
            // Pantalla original de lista de personajes
            LazyColumn(modifier = modifier) {
                items(characterState.data) { character ->
                    CharacterItem(
                        character = character,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onCharacterClick(character.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier
) {
    val imageBackgroundColors = listOf(
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.inverseSurface
    )
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            color = imageBackgroundColors[(character.id % imageBackgroundColors.size)],
            shape = CircleShape
        ) {
            Box {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Character Image",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Column {
            Text(text = character.name)
            Text(
                text = "${character.species} * ${character.status}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCharacterListScreen() {
    laboratorio9Theme {
        Surface {
            val db = CharacterDb()
            CharacterListScreen(
                characterState = CharacterState(data = db.getAllCharacters().take(6)),
                onCharacterClick = {},
                onLoadingClick = {},
                onRetry = {},
                modifier = Modifier.fillMaxSize())
        }


    }
}
