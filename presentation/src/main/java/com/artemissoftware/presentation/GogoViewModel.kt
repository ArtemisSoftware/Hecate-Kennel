package com.artemissoftware.presentation

import androidx.lifecycle.ViewModel
import com.artemissoftware.domain.usecase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GogoViewModel  @Inject constructor(private val getDogsUseCase: GetDogsUseCase) :
    ViewModel(){


    init {

        val a = 0

        var f = a +1

    }


    fun jojo(){

        val r = 0
    }
}