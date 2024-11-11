package com.example.laboratorio9.data.network

import com.example.laboratorio9.data.network.dto.CharacterDto
import com.example.laboratorio9.data.network.dto.LocationDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.call.body

class RickAndMortyApi(private val client: HttpClient) {

    suspend fun getCharacters(): CharacterDto {
        return client.get("https://rickandmortyapi.com/api/character").body()
    }

    suspend fun getLocations(): LocationDto {
        return client.get("https://rickandmortyapi.com/api/location").body()
    }
}
