package com.example
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio9.bottom.BottomNavigationBar
import com.example.laboratorio9.location.locationNavigation
import com.example.laboratorio9.characters.characterNavigation
import com.example.laboratorio9.characterDetails.characterDetailsNavigation
import com.example.laboratorio9.characterDetails.navigateToCharacterDetail
import com.example.laboratorio9.location.navigateToLocationDetail
import com.example.laboratorio9.login.loginNavigation
import com.example.laboratorio9.profile.profileNavigation
import com.example.laboratorio9.ui.theme.laboratorio9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            laboratorio9Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            currentRoute = "characters",
                            onNavItemClick = { route ->
                                navController.navigate(route)
                            }
                        )
                    }
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        loginNavigation(navController = navController)
                        characterNavigation(
                            onCharacterClick = { characterId ->
                                navController.navigateToCharacterDetail(characterId)
                            },
                            onBackToLogin = {
                                finish()
                            }
                        )
                        characterDetailsNavigation(onNavigateBack = { navController.navigateUp() })
                        locationNavigation(
                            onLocationClick = { locationId ->
                                navController.navigateToLocationDetail(locationId)
                            }
                        )
                        profileNavigation(navController = navController)
                    }
                }
            }
        }
    }
}
