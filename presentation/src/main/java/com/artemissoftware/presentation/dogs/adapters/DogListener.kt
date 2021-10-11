package com.artemissoftware.presentation.dogs.adapters

import com.artemissoftware.domain.model.Dog

interface DogListener {

    fun onItemClick(dog: Dog)
}