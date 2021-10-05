package com.artemissoftware.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.common.Resource
import com.artemissoftware.domain.usecase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GogoViewModel  @Inject constructor(private val getDogsUseCase: GetDogsUseCase) :
    ViewModel(){


    init {

        val a = 0

        var f = a +1

    }


     fun getDogs() {

        getDogsUseCase().onEach { result ->

            val a = 0

            var f = a +1

            when (result) {
                is Resource.Success -> {
//                    _state.value = CoinsListState(coins = result.data ?: emptyList())
                }
//                is Resource.Error -> {
//                    _state.value = CoinsListState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//                is Resource.Loading -> {
//                    _state.value = CoinsListState(isLoading = true)
//                }
            }
        }.launchIn(viewModelScope)
    }

}