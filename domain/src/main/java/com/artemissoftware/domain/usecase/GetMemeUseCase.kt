package com.artemissoftware.domain.usecase

import com.artemissoftware.common.Resource
import com.artemissoftware.domain.model.Meme
import com.artemissoftware.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMemeUseCase @Inject constructor(private val repository: DogRepository) {

    operator fun invoke(): Flow<Resource<Meme>> = flow {

        val result = repository.getMeme()
        emit(Resource.Success(result))

    }
}