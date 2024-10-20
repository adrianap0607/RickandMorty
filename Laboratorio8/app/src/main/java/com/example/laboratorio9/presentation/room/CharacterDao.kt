package com.example.laboratorio9.presentation.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    // Insertar múltiples personajes en la base de datos con coroutines
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    // Consultar todos los personajes desde la base de datos
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    // Consultar un personaje por su ID
    @Query("SELECT * FROM characters WHERE id = :characterId")
    fun getCharacterById(characterId: Int): Flow<CharacterEntity?>
}
