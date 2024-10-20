package com.example.laboratorio9


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.laboratorio9.data.source.CharacterDb
import com.example.laboratorio9.data.source.LocationDb
import com.example.laboratorio9.presentation.login.DataStoreUserPrefs
import com.example.laboratorio9.ui.theme.laboratorio9Theme
import com.example.laboratorio9.presentation.navigation.AppNavigation
import com.example.laboratorio9.presentation.room.AppDatabase

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPrefs = DataStoreUserPrefs(dataStore);
        val db = AppDatabase.getDatabase(this)
        val characterDao = db.characterDao()
        val locationDao = db.locationDao()
        val characterDb = CharacterDb()
        val locationDb = LocationDb()

        setContent {
            val navController = rememberNavController()

            laboratorio9Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navController = navController,
                        userPrefs = userPrefs,
                        characterDao = characterDao,
                        locationDao = locationDao,
                        characterDb = characterDb,
                        locationDb = locationDb
                    )

            }
        }
    }
}}