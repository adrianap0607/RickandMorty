package com.example.laboratorio9.presentation.mainFlow.location.profile

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio9.data.model.Location
import com.example.laboratorio9.presentation.mainFlow.ErrorScreen
import com.example.laboratorio9.presentation.mainFlow.LoadingScreen
import com.example.laboratorio9.ui.theme.laboratorio9Theme

@Composable
fun LocationProfileRoute(
    onNavigateBack: () -> Unit,
    viewModel: LocationProfileViewModel = viewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LocationProfileScreen(
        state = state,
        onGetInfoClick = { viewModel.getLocationData() },
        onNavigateBack = onNavigateBack,
        onLoadingClick = { viewModel.onLoadingClick() },
        onRetryClick = { viewModel.retryLoading() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationProfileScreen(
    state: LocationProfileState,
    onGetInfoClick: () -> Unit,
    onNavigateBack: () -> Unit,
    onLoadingClick: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = { Text("Location Details") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
        LocationProfileContent(
            state = state,
            onGetInfoClick = onGetInfoClick,
            onLoadingClick = onLoadingClick,
            onRetryClick = onRetryClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun LocationProfileContent(
    state: LocationProfileState,
    onGetInfoClick: () -> Unit,
    onLoadingClick: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        when {
            state.isLoading -> {

                LoadingScreen(modifier = Modifier.clickable { onLoadingClick() })
            }

            state.hasError -> {

                ErrorScreen(
                    errorMessage = "Error al cargar la información de la ubicación. Intenta de nuevo.",
                    onRetry = onRetryClick
                )
            }

            state.data == null -> {

                Button(
                    onClick = onGetInfoClick,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text("Obtener información")
                }
            }

            else -> {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = state.data.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LocationProfilePropItem(
                        title = "ID:",
                        value = state.data.id.toString(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    LocationProfilePropItem(
                        title = "Type:",
                        value = state.data.type,
                        modifier = Modifier.fillMaxWidth()
                    )
                    LocationProfilePropItem(
                        title = "Dimensions:",
                        value = state.data.dimension,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun LocationProfilePropItem(
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
@Composable
private fun PreviewLocationProfileScreen() {
    laboratorio9Theme {
        Surface {
            LocationProfileScreen(
                state = LocationProfileState(
                    isLoading = false,
                    data = Location(1, "Earth (C-137)", "Planet", "Dimension C-137")
                ),
                onNavigateBack = { },
                onGetInfoClick = { },
                onLoadingClick = { },
                onRetryClick = { },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoadingLocationProfileScreen() {
    laboratorio9Theme {
        Surface {
            LocationProfileScreen(
                state = LocationProfileState(
                    isLoading = true,
                    data = null
                ),
                onNavigateBack = { },
                onGetInfoClick = { },
                onLoadingClick = { },
                onRetryClick = { },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun PreviewEmptyLocationProfileScreen() {
    laboratorio9Theme {
        Surface {
            LocationProfileScreen(
                state = LocationProfileState(
                    isLoading = false,
                    data = null
                ),
                onNavigateBack = { },
                onGetInfoClick = { },
                onLoadingClick = { },
                onRetryClick = { },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
