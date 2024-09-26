package com.example.laboratorio9.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.laboratorio9.ui.theme.laboratorio9Theme

@Composable
fun ProfileScreen(navController: NavController, onLogoutClick: () -> Unit = {
    // Navegar a la pantalla de login y limpiar el backstack
    navController.navigate("login") {
        popUpTo("login") { inclusive = true } // Esto limpia el backstack
    }
}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)  // Se añade el color de fondo
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.Person, contentDescription = "Profile", modifier = Modifier.size(128.dp))
        Spacer(modifier = Modifier.height(16.dp))

        // Sección con el nombre y el carné en filas
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Nombre:", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                Text(text = "Adriana Palacios", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Carné:", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                Text(text = "23044", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para cerrar sesión
        Button(onClick = onLogoutClick) {
            Text("Cerrar sesión")
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    laboratorio9Theme {
        // Previsualización sin la acción de logout
        ProfileScreen(navController = NavController(null))
    }
}
