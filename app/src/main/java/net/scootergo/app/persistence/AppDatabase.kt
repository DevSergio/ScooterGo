package net.scootergo.app.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import net.scootergo.app.model.Scooter


@Database(entities = [Scooter::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun scooterDao(): ScooterDao
}
