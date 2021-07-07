package net.scootergo.app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.scootergo.app.persistence.AppDatabase
import net.scootergo.app.persistence.ScooterDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context
    ): AppDatabase {
        return Room
            .databaseBuilder(appContext, AppDatabase::class.java, "Scooter.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideScooterDao(appDatabase: AppDatabase): ScooterDao {
        return appDatabase.scooterDao()
    }
}