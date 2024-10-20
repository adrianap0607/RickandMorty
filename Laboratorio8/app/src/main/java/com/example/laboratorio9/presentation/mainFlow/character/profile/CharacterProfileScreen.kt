package com.example.laboratorio9.presentation.mainFlow.character.profile

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio9.data.model.Character
import com.example.laboratorio9.ui.theme.laboratorio9Theme
import com.example.laboratorio9.presentation.mainFlow.ErrorScreen
import com.example.laboratorio9.presentation.mainFlow.LoadingScreen

@Composable
fun CharacterProfileRoute(
    id: Int,
    onNavigateBack: () -> Unit,
    viewModel: CharacterProfileViewModel = viewModel() // Conectar con el ViewModel del perfil
) {
    // Observar el estado del ViewModel
    val profileState by viewModel.characterProfileState.collectAsState()

    // Llamar a la función para cargar el perfil del personaje según el ID
    viewModel.getCharacterProfile(id)

    // Mostrar la pantalla según el estado (carga, error, o datos cargados)
    CharacterProfileScreen(
        profileState = profileState,
        onNavigateBack = onNavigateBack,
        onRetry = { viewModel.retryLoading(id) }, // Callback para reintentar cargar el perfil
        onLoadingClick = { viewModel.onLoadingClick() },
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterProfileScreen(
    profileState: CharacterProfileState,
    onNavigateBack: () -> Unit,
    onRetry: () -> Unit,
    onLoadingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        profileState.isLoading -> {
            // Pantalla de carga con opción para activar error al hacer clic
            LoadingScreen(modifier = modifier.clickable { onLoadingClick() })
        }
        profileState.hasError -> {
            // Pantalla de error con opción para reintentar
            ErrorScreen(
                errorMessage = "Error al obtener perfil de personaje. Intenta de nuevo.",
                onRetry = onRetry
            )
        }
        else -> {
            // Pantalla original con detalles del personaje (layout sin cambios)
            Column(
                modifier = modifier
            ) {
                TopAppBar(
                    title = {
                        Text("Character Detail")
                    },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(Icons.Default.ArrowBack, contentDescription = null)
                        }
                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer, shape = CircleShape)
                    ) {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = "Person",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = profileState.data.firstOrNull()?.name ?: "Unknown",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CharacterProfilePropItem(
                        title = "Species:",
                        value = profileState.data.firstOrNull()?.species ?: "Unknown",
                        modifier = Modifier.fillMaxWidth()
                    )
                    CharacterProfilePropItem(
                        title = "Status:",
                        value = profileState.data.firstOrNull()?.status ?: "Unknown",
                        modifier = Modifier.fillMaxWidth()
                    )
                    CharacterProfilePropItem(
                        title = "Gender:",
                        value = profileState.data.firstOrNull()?.gender ?: "Unknown",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun CharacterProfilePropItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = value)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCharacterProfileScreen() {
    laboratorio9Theme {
        Surface {
            CharacterProfileScreen(
                profileState = CharacterProfileState(
                    isLoading = false,
                    data = listOf(
                        Character(
                            id = 2565,
                            name = "Rick",
                            status = "Alive",
                            species = "Human",
                            gender = "Male",
                            image = ""
                        )
                    )
                ),
                onNavigateBack = { },
                onRetry = {},
                onLoadingClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
