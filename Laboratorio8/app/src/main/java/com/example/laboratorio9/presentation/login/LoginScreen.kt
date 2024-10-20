package com.example.laboratorio9.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio9.R
import com.example.laboratorio9.data.source.CharacterDb
import com.example.laboratorio9.data.source.LocationDb
import com.example.laboratorio9.presentation.room.CharacterDao
import com.example.laboratorio9.presentation.room.LocationDao
import com.example.laboratorio9.ui.theme.laboratorio9Theme
import com.example.laboratorio9.data.model.Character


@Composable
fun LoginRoute(
    onLoginClick: (String) -> Unit,
    userPrefs: DataStoreUserPrefs,
    characterDao: CharacterDao,
    locationDao: LocationDao,
    characterDb: CharacterDb,
    locationDb: LocationDb
) {

    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(userPrefs)
    )
    var name by remember { mutableStateOf("") }
    var isSyncing by remember { mutableStateOf(false)}


    LoginScreen(
        name = name,
        onNameChange = { name = it },
        onLoginClick = {
            isSyncing = true
            viewModel.saveUserName(name)


            val characters = characterDb.getAllCharacters()
            val locations = locationDb.getAllLocations()

            viewModel.syncData(characters, locations, characterDao, locationDao) {
                isSyncing = false
                onLoginClick(name)
            }
        },
        modifier = Modifier.fillMaxSize(),
        isSyncing = isSyncing
    )
}

@Composable
private fun LoginScreen(
    name: String,
    onNameChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSyncing: Boolean = false
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.rickandmorty), contentDescription = "Logo")

            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                placeholder = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            if (isSyncing){
                CircularProgressIndicator()
            } else{
                Button(
                    onClick = onLoginClick,
                    modifier=Modifier.fillMaxWidth()
                ) {
                    Text(text = "Iniciar sesión")

                }
            }

        }
        Text(
            text = "Adriana Palacios - #23044",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp))
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoginScreen() {
    laboratorio9Theme {
        Surface {
            LoginScreen(
                name = "",
                onNameChange = {},
                onLoginClick = { /*TODO*/ },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
