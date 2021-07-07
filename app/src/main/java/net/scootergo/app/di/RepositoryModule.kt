 package net.scootergo.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import net.scootergo.app.persistence.ScooterDao
import net.scootergo.app.repository.MainRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        scooterDao: ScooterDao
    ): MainRepository {
        return MainRepository(scooterDao)
    }
}