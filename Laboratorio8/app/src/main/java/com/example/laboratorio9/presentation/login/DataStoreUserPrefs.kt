package com.example.laboratorio9.presentation.login


import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import androidx.datastore.core.DataStore

class DataStoreUserPrefs(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val NAME_KEY = stringPreferencesKey("name")
    }

    //funcion que sirve para guardar el nombre ingresado
    suspend fun setName(name: String) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = name

        }
    }
    //funcion para obtener el nombre

    fun getName(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[NAME_KEY]
        }
    }

    suspend fun clearName(){
        dataStore.edit { preferences ->
            preferences.remove(NAME_KEY)

        }
    }
}
