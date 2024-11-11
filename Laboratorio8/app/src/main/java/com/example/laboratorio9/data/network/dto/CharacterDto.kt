package com.example.laboratorio9.data.network.dto

import com.example.laboratorio9.presentation.room.CharacterEntity
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginDto,
    val location: CharacterLocationDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

// Función para mapear a CharacterEntity
fun CharacterDto.mapToCharacterModel(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}

@Serializable
data class OriginDto(
    val name: String,
    val url: String
)

@Serializable
data class CharacterLocationDto(
    val name: String,
    val url: String
)

@Serializable
data class CharactersResponseDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)

@Serializable
data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
