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
import com.example.laboratorio9.ui.theme.laboratorio9Theme

@Composable
fun profileRoute(
    onLogoutClick: () -> Unit
){
    ProfileScreen(onLogoutClick = onLogoutClick)
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onLogoutClick: () -> Unit ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)  // Se añade el color de fondo
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(modifier = Modifier.fillMaxWidth()){
            Text(
                text = "Profile",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp))
        }

        // Sección con el nombre y el carné en filas
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = "Profile", modifier = Modifier.size(128.dp))
            Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para cerrar sesión
            Button(onClick = onLogoutClick) {
                Text("Cerrar sesión")
            }

        }


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    laboratorio9Theme {
        ProfileScreen(onLogoutClick = {})
    }
}