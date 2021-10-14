package com.artemissoftware.domain.usecase

import com.artemissoftware.common.Resource
import com.artemissoftware.domain.errors.DataException
import com.artemissoftware.domain.model.Meme
import com.artemissoftware.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMemeUseCase @Inject constructor(private val repository: DogRepository) {

    operator fun invoke(): Flow<Resource<Meme>> = flow {

        val apiResult = repository.getMeme()

        apiResult.data ?: run {

            throw DataException()

//            when (apiResult.error.message) {
//                //--INVALID_EMAIL_OR_NIF -> throw InvalidEmailNifException()
//                //--else -> throw UnknownAPIException()
//            }
        }

//        emit(Resource.Success(result.))

    }
}