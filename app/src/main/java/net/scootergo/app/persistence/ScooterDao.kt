package net.scootergo.app.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.scootergo.app.model.Scooter

@Dao
interface ScooterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScooterList(scooterList : List<Scooter>)

    @Query("SELECT * FROM Scooter")
    suspend fun getScooterList(): List<Scooter>
}