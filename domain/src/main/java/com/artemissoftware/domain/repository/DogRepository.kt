package com.artemissoftware.domain.repository

import com.artemissoftware.domain.ApiNetworkResponse
import com.artemissoftware.domain.model.Dog
import com.artemissoftware.domain.model.Meme

interface DogRepository {

    suspend fun getDogs(): List<Dog>

    suspend fun getMeme(): ApiNetworkResponse<Meme>
}