package com.artemissoftware.repository

import com.artemissoftware.domain.model.Dog
import com.artemissoftware.domain.repository.DogRepository

class DogRepositoryImpl : DogRepository{

    override suspend fun getDogs(): List<Dog> {
        TODO("Not yet implemented")
    }
}