package com.artemissoftware.mappers

import com.artemissoftware.domain.model.Dog
import com.artemissoftware.presentation.details.models.DogDetail

fun Dog.toDogDetail(): DogDetail {

    return DogDetail(
        name = name,
        breedGroup = breedGroup,
        temperament = temperament,
        imageUrl = imageUrl,
    )
}