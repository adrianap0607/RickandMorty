package com.example.laboratorio9.data.network.dto


import com.example.laboratorio9.presentation.room.LocationEntity
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)

// Función para mapear a LocationEntity
fun LocationDto.mapToLocationModel(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}

@Serializable
data class LocationsResponseDto(
    val info: InfoDto,
    val results: List<LocationDto>
)

@Serializable
data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
