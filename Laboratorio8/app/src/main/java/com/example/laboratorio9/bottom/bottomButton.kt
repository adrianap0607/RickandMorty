package com.example.laboratorio9.bottom

import com.example.laboratorio9.ui.theme.laboratorio9Theme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.ui.tooling.preview.Preview
import com.example.laboratorio9.characters.CharacterDestination
import com.example.laboratorio9.location.locationDestination
import com.example.laboratorio9.profile.ProfileDestination

sealed class BottomNavItem(val label: String, val icon: ImageVector, val route: Any) {
    object Characters : BottomNavItem("Characters", Icons.Filled.People, CharacterDestination)
    object Locations : BottomNavItem("Locations", Icons.Filled.Public, locationDestination)
    object Profile : BottomNavItem("Profile", Icons.Filled.Person, ProfileDestination)
}

@Composable
fun BottomNavigationBar(
    onNavItemClick: (Any) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer, // Color de fondo
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer // Color del contenido
    ) {
        val items = listOf(
            BottomNavItem.Characters,
            BottomNavItem.Locations,
            BottomNavItem.Profile
        )
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,  // Color del icono seleccionado
                    selectedTextColor = MaterialTheme.colorScheme.secondary,  // Color del texto seleccionado
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,  // Color del icono no seleccionado
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer  // Color del texto no seleccionado
                ),
                selected = item.route == onNavItemClick,
                onClick = { onNavItemClick(item.route) }
            )
        }
    }
}