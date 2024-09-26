package com.example.laboratorio9.location

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laboratorio9.locationinformation.Location
import com.example.laboratorio9.locationinformation.LocationDb
import com.example.laboratorio9.ui.theme.laboratorio9Theme
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun locationRoute(onLocationClick: (Int) -> Unit) {
    LocationScreen(onLocationClick = onLocationClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(onLocationClick: (Int) -> Unit) {
    val locationDb = LocationDb()
    val locations = locationDb.getAllLocations()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = "Locations",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* Acción de retroceso */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(locations) { location ->
                LocationRow(location = location, onClick = { onLocationClick(location.id) })
            }
        }
    }
}

@Composable
fun LocationRow(location: Location, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(
            text = location.name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = location.type,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationScreen() {
    laboratorio9Theme {
        LocationScreen(onLocationClick = {})
    }
}
