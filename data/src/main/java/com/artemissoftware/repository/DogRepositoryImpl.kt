package com.artemissoftware.repository

import com.artemissoftware.HandleApi
import com.artemissoftware.HandleApi.safeApiCall
import com.artemissoftware.common.ApiConstants.NUMBER_OF_RESULTS_PER_PAGE
import com.artemissoftware.domain.ApiNetworkResponse
import com.artemissoftware.domain.model.Dog
import com.artemissoftware.domain.model.Meme
import com.artemissoftware.domain.repository.DogRepository
import com.artemissoftware.errors.HecateKennelApiNetworkException
import com.artemissoftware.mappers.toApiNetworkError
import com.artemissoftware.mappers.toDog
import com.artemissoftware.mappers.toMeme
import com.artemissoftware.remote.DogApi
import com.artemissoftware.remote.sources.DogApiSource
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor (private val dogApiSource: DogApiSource): DogRepository{

    override suspend fun getDogs(): ApiNetworkResponse<List<Dog>> {

        return try {

            val result = dogApiSource.getDogs()
            ApiNetworkResponse(result.filter { item -> item.breeds.isNotEmpty() }.map { it.toDog() })

        } catch (ex: HecateKennelApiNetworkException) {
            ApiNetworkResponse(error = ex.toApiNetworkError())
        }
    }

    override suspend fun getMeme(): ApiNetworkResponse<Meme> {

        return try {

            val result = dogApiSource.getMeme()
            ApiNetworkResponse(result.map { it.toMeme() }.first())

        } catch (ex: HecateKennelApiNetworkException) {
            ApiNetworkResponse(error = ex.toApiNetworkError())
        }
    }

}