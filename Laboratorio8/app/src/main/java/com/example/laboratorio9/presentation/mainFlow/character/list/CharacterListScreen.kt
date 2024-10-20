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
import com.example.laboratorio9.data.source.CharacterDb
import com.example.laboratorio9.presentation.room.CharacterDao
import com.example.laboratorio9.presentation.room.CharacterEntity
import com.example.laboratorio9.ui.theme.laboratorio9Theme

@Composable
fun CharacterListRoute(
    characterDao: CharacterDao,
    onCharacterClick: (Int) -> Unit,
) {
    // Crear el ViewModel utilizando CharacterDao
    val viewModel: CharacterListViewModel = viewModel(
        factory = CharacterListViewModelFactory(characterDao)
    )

    // Observar el estado del ViewModel
    val characterState by viewModel.characterState.collectAsStateWithLifecycle()

    // Llamar a la pantalla pasando los parámetros correctos
    CharacterListScreen(
        characters = characterState.data, // Ajustado para ser lista de CharacterEntity
        onCharacterClick = onCharacterClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun CharacterListScreen(
    characters: List<CharacterEntity>, // Ajustar el tipo a CharacterEntity
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(characters) { characterEntity ->
            // Ajustar la función de lista para trabajar con CharacterEntity
            CharacterItem(
                character = characterEntity, // CharacterEntity en lugar de Character
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCharacterClick(characterEntity.id) }
            )
        }
    }
}

@Composable
private fun CharacterItem(
    character: CharacterEntity, // Ajustar a CharacterEntity
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
                characters = db.getAllCharacters().take(6), // Trabaja con CharacterEntity
                onCharacterClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
