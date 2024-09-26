package com.example.laboratorio9.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(onLoginClick: () -> Unit) {
    composable<LoginDestination>{
        LoginRoute(
            onLoginClick = onLoginClick
        )
    }
}

//Función para que no se muestra el nav bar
fun getSpecificRouteLogIn(): String {
    return "com.example.laboratorio9.login.LoginDestination"
}