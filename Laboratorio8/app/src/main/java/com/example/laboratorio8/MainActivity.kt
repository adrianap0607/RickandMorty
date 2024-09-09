package com.example.laboratorio8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio8.characters.characterNavigation
import com.example.laboratorio8.characterDetails.characterDetailsNavigation
import com.example.laboratorio8.characterDetails.navigateToCharacterDetail
import com.example.laboratorio8.login.loginNavigation
import com.example.laboratorio8.ui.theme.laboratorio8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            laboratorio8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login", // Empieza en la pantalla de Login
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        // Pantalla de Login
                        loginNavigation(navController = navController)

                        // Pantalla de personajes
                        characterNavigation(
                            onCharacterClick = { characterId ->
                                navController.navigateToCharacterDetail(characterId)
                            },
                            onBackToLogin = {

                                finish()
                            }
                        )

                        // Pantalla de detalles del personaje
                        characterDetailsNavigation(onNavigateBack = { navController.navigateUp() })
                    }
                }
            }
        }
    }
}
