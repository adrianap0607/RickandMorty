package com.example.laboratorio9.bottom
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.tooling.preview.Preview
import com.example.laboratorio9.ui.theme.laboratorio9Theme

sealed class BottomNavItem(val label: String, val icon: ImageVector, val route: String) {
    object Characters : BottomNavItem("Characters", Icons.Filled.People, "characters")
    object Locations : BottomNavItem("Locations", Icons.Filled.Public, "locations")
    object Profile : BottomNavItem("Profile", Icons.Filled.Person, "profile")
}

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavItemClick: (String) -> Unit
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
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,  // Color del icono seleccionado
                    selectedTextColor = MaterialTheme.colorScheme.secondary,  // Color del texto seleccionado
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,  // Color del icono no seleccionado
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer  // Color del texto no seleccionado
                ),
                onClick = { onNavItemClick(item.route) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavigationBar() {
    laboratorio9Theme {
        var currentRoute by remember { mutableStateOf(BottomNavItem.Characters.route) }
        BottomNavigationBar(currentRoute = currentRoute, onNavItemClick = { currentRoute = it })
    }
}
