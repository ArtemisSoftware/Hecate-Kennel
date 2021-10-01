package com.artemissoftware.repository

import com.artemissoftware.domain.model.Dog
import com.artemissoftware.domain.repository.DogRepository
import com.artemissoftware.mappers.toDog
import com.artemissoftware.remote.DogApi
import javax.inject.Inject

class DogRepositoryImpl (private val dogApi: DogApi): DogRepository{

    override suspend fun getDogs(): List<Dog> {
        return dogApi.getDogs("10").map { it.toDog() }
    }
}