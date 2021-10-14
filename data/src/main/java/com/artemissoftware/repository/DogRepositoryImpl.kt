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
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor (private val dogApi: DogApi): DogRepository{

    override suspend fun getDogs(): List<Dog> {
        return dogApi.getDogs(NUMBER_OF_RESULTS_PER_PAGE).filter { item -> item.breeds.isNotEmpty() }.map { it.toDog() }
    }

    override suspend fun getMeme(): ApiNetworkResponse<Meme> {


        return try {
            val result = safeApiCall {
                dogApi.getMeme()
            }
            ApiNetworkResponse(result.map { it.toMeme() }.first())
        } catch (ex: HecateKennelApiNetworkException) {
            ApiNetworkResponse(error = ex.toApiNetworkError())
        }




//        return safeApiCall {
//            dogApi.getMeme().map { it.toMeme() }.first()
//        }

        //return dogApi.getMeme().map { it.toMeme() }.first()
    }


}