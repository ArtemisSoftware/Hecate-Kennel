package com.artemissoftware.domain.usecase

import com.artemissoftware.domain.repository.DogRepository

class GetDogsUseCase constructor(private val repository: DogRepository) {

    operator fun invoke(){
         val i = 0
        val j = 2
    }

}