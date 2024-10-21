package com.example.laboratorio9.presentation.room



import com.example.laboratorio9.data.model.Character
import com.example.laboratorio9.data.model.Location


fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,
        image = ""

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
