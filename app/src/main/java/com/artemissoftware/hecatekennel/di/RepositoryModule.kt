package com.artemissoftware.hecatekennel.di

import com.artemissoftware.domain.repository.DogRepository
import com.artemissoftware.remote.DogApi
import com.artemissoftware.repository.DogRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideDogRepository(api: DogApi): DogRepository {
        return DogRepositoryImpl(api)
    }

}