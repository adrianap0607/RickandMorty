package com.example.laboratorio9.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import com.example.laboratorio9.ui.theme.laboratorio9Theme
import com.example.laboratorio9.R
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun profileRoute(
    onLogoutClick: () -> Unit
){
    ProfileScreen(onLogoutClick = onLogoutClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onLogoutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(170.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
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
