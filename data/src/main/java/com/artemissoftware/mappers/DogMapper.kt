package com.artemissoftware.mappers

import com.artemissoftware.domain.model.Dog
import com.artemissoftware.remote.dto.DogDto

fun DogDto.toDog(): Dog {

    val breed = breeds[0]

    return Dog(
        name = breed.name,
        breedGroup = breed.breedGroup ?: "",
        temperament = breed.temperament ?: "",
        lifeSpan = breed.lifeSpan ?: "",
        imageUrl = url,
    )
}