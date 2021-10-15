package com.artemissoftware.remote.sources

import com.artemissoftware.HandleApi.safeApiCall
import com.artemissoftware.remote.DogApi
import com.artemissoftware.remote.dto.DogDto
import com.artemissoftware.remote.dto.MemeDto
import javax.inject.Inject

class DogApiSourceImpl @Inject constructor (private val dogApi: DogApi): DogApiSource {


    override suspend fun getDogs(): List<DogDto> {
        return safeApiCall {
            dogApi.getDogs()
        }
    }

    override suspend fun getMeme(): List<MemeDto> {
        return safeApiCall {
            dogApi.getMeme()
        }
    }

}