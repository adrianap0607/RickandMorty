package com.example.laboratorio9.presentation.room



import com.example.laboratorio9.data.model.Character
import com.example.laboratorio9.data.model.Location


fun Character.toCharacter(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,

    )
}

fun LocationEntity.toLocation(): Location {
    return Location(
        id = this.id,
        name = this.name,
        type = this.type,
        dimension = this.dimension
    )
}
