package com.artemissoftware.remote.sources

import com.artemissoftware.remote.dto.DogDto
import com.artemissoftware.remote.dto.MemeDto


interface DogApiSource {

    suspend fun getDogs(): List<DogDto>
    suspend fun getMeme(): List<MemeDto>
}