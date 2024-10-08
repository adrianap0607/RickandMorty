package com.example.laboratorio8.characterDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio8.informacion.CharacterDb
import com.example.laboratorio8.ui.theme.laboratorio8Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    onBackClick: () -> Unit
) {
    val character = CharacterDb().getCharacterById(characterId)


    val circleColor = when (characterId % 5) {
        0 -> MaterialTheme.colorScheme.primary
        1 -> MaterialTheme.colorScheme.secondary
        2 -> MaterialTheme.colorScheme.tertiary
        3 -> MaterialTheme.colorScheme.primaryContainer
        4 -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.primary
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Character Detail", color = MaterialTheme.colorScheme.onPrimary)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(circleColor, CircleShape) // Color del círculo
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = character.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Species: ${character.species}")
            Text(text = "Status: ${character.status}")
            Text(text = "Gender: ${character.gender}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterDetailScreen() {
    laboratorio8Theme {
        CharacterDetailScreen(characterId = 1, onBackClick = {})
    }
}
