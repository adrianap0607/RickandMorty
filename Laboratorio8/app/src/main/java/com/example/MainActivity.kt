package com.example
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio9.bottom.BottomNavigationBar
import com.example.laboratorio9.characterDetails.CharacterInfoDestination
import com.example.laboratorio9.characterDetails.characterInfoScreen
import com.example.laboratorio9.characterDetails.navigateToCharacterDetailsScreen
import com.example.laboratorio9.characters.CharacterDestination
import com.example.laboratorio9.characters.characterScreen
import com.example.laboratorio9.location.locationDestination
import com.example.laboratorio9.location.locationScreen
import com.example.laboratorio9.locationDetails.locationInfoScreen
import com.example.laboratorio9.locationDetails.locationinfoDestination
import com.example.laboratorio9.locationDetails.navigateToLocationInfoScreen
import com.example.laboratorio9.login.LoginDestination
import com.example.laboratorio9.login.getSpecificRouteLogIn
import com.example.laboratorio9.login.loginScreen
import com.example.laboratorio9.profile.profileScreen
import com.example.laboratorio9.ui.theme.laboratorio9Theme
import kotlinx.serialization.Serializable

@Serializable
data object character_Graph
@Serializable
data object location_Graph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            laboratorio9Theme {
                val navController = rememberNavController()
                val currentDestination = navController.currentBackStackEntryAsState().value?.destination
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if(currentDestination?.route != getSpecificRouteLogIn())
                            BottomNavigationBar(onNavItemClick = {
                                navController.navigate(it)
                            })
                    }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = LoginDestination, // Empieza en la pantalla de Login
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        // Pantalla de Login
                        loginScreen(onLoginClick = {
                            navController.navigate(CharacterDestination){
                                popUpTo(LoginDestination){
                                    inclusive = true
                                }
                            }
                        })
                        //Subgráfico de personajes
                        navigation<character_Graph>(startDestination = CharacterDestination){
                            characterScreen(
                                onCharacterClick = { character ->

                                    navController.navigateToCharacterDetailsScreen(
                                        destination = CharacterInfoDestination(
                                            characterId = character
                                        )
                                    )

                                }
                            )
                            characterInfoScreen(
                                onBackClick = {
                                    navController.navigateUp()
                                }
                            )
                        }

                        //Subgráfico de ubicaciones
                        navigation<location_Graph>(startDestination = locationDestination){
                            locationScreen(
                                onLocationClick = { location ->
                                    navController.navigateToLocationInfoScreen(
                                        destination = locationinfoDestination(
                                            locationId = location
                                        )
                                    )

                                }
                            )
                            locationInfoScreen(
                                onBackClick = {
                                    navController.navigateUp()
                                }
                            )
                        }

                        //Pantalla del perfil
                        profileScreen(
                            onLogoutClick = {
                                navController.navigate(LoginDestination){
                                    popUpTo(0)
                                }
                            }
                        )


                    }
                }
            }
        }
    }

}