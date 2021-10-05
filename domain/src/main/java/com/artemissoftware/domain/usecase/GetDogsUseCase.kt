package com.artemissoftware.domain.usecase

import com.artemissoftware.common.Resource
import com.artemissoftware.domain.model.Dog
import com.artemissoftware.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(private val repository: DogRepository) {

    operator fun invoke(): Flow<Resource<List<Dog>>> = flow {

        val result = repository.getDogs()
        emit(Resource.Success(result))

    }

}