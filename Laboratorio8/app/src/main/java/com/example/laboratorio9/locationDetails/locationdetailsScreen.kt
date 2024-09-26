package com.example.laboratorio9.locationDetails

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio9.locationinformation.LocationDb
import com.example.laboratorio9.ui.theme.laboratorio9Theme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api

@Composable
fun LocationDetailRoute(locationId: Int, onBackClick: () -> Unit){
    LocationDetailScreen(locationId = locationId, onBackClick = onBackClick)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(locationId: Int, onBackClick: () -> Unit) {
    val locationDb = LocationDb()
    val location = locationDb.getLocationById(locationId)

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {


        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = "Location Details",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Name: ${location.name}",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Type: ${location.type}",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Dimension: ${location.dimension}",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationDetailScreen() {
    laboratorio9Theme {
        LocationDetailScreen(locationId = 1, onBackClick = {})
    }
}

