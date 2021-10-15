package com.artemissoftware.domain.usecase

import com.artemissoftware.common.Resource
import com.artemissoftware.domain.errors.DataException
import com.artemissoftware.domain.errors.UnknownAPIException
import com.artemissoftware.domain.model.Meme
import com.artemissoftware.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMemeUseCase @Inject constructor(private val repository: DogRepository) {

    operator fun invoke(): Flow<Resource<Meme>> = flow {

        val apiResult = repository.getMeme()

        apiResult.data ?: run {

            when (apiResult.error.code) {
                400 -> throw DataException()
                else -> throw UnknownAPIException(apiResult.error.message)
            }
        }

        emit(Resource.Success(apiResult.data!!))

    }

}