package com.artemissoftware.remote

import com.artemissoftware.common.ApiConstants.MIME_TYPE
import com.artemissoftware.common.ApiConstants.NUMBER_OF_RESULTS_PER_PAGE
import com.artemissoftware.remote.dto.DogDto
import com.artemissoftware.remote.dto.MemeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi {


    @GET("v1/images/search")
    suspend fun getDogs(@Query("limit") limit: String = NUMBER_OF_RESULTS_PER_PAGE, @Query("order") order: String = "Desc"): List<DogDto>


    @GET("v1/images/search")
    suspend fun getMeme(@Query("mime_types") mimeType: String = MIME_TYPE): List<MemeDto>
}