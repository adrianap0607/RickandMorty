package com.example.laboratorio9.presentation.room
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    // Insertar múltiples ubicaciones en la base de datos con coroutines
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<LocationEntity>)

    // Consultar todas las ubicaciones desde la base de datos
    @Query("SELECT * FROM locations")
    fun getAllLocations(): Flow<List<LocationEntity>>

    // Consultar una ubicación por su ID
    @Query("SELECT * FROM locations WHERE id = :locationId")
    fun getLocationById(locationId: Int): Flow<LocationEntity?>
}
