package com.artemissoftware.remote

import com.artemissoftware.remote.dto.DogDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi {


    @GET("v1/images/search")
    suspend fun getDogs(@Query("limit") limit: String, @Query("order") order: String = "Desc"): List<DogDto>

}