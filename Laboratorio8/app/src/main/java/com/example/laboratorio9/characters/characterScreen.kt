package com.example.laboratorio9.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio9.informacion.Character
import com.example.laboratorio9.informacion.CharacterDb
import com.example.laboratorio9.ui.theme.laboratorio9Theme
import com.example.laboratorio9.ui.theme.laboratorio9Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(onCharacterClick: (Int) -> Unit) {
    val characterDb = CharacterDb()
    val characterList = characterDb.getAllCharacters()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Characters", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(35.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(characterList) { character ->
                CharacterRow(character = character, onClick = { onCharacterClick(character.id) })
            }
        }
    }
}

@Composable
fun CharacterRow(character: Character, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val circleColor = when (character.id % 5) {
            0 -> MaterialTheme.colorScheme.primary
            1 -> MaterialTheme.colorScheme.secondary
            2 -> MaterialTheme.colorScheme.tertiary
            3 -> MaterialTheme.colorScheme.primaryContainer
            4 -> MaterialTheme.colorScheme.secondaryContainer
            else -> MaterialTheme.colorScheme.primary
        }

        Box(
            modifier = Modifier
                .size(65.dp)
                .background(circleColor, CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = character.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(text = "${character.species} - ${character.status}", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharactersScreen() {
    laboratorio9Theme {
        CharactersScreen(onCharacterClick = {})
    }
}
