package com.example.laboratorio9.presentation.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.net.ssl.SSLEngineResult.Status

@Entity (tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,

)



