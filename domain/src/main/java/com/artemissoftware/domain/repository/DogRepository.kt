package com.artemissoftware.domain.repository

import com.artemissoftware.domain.model.Dog

interface DogRepository {

    suspend fun getDogs(): List<Dog>
}