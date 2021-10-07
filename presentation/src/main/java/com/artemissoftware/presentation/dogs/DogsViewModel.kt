package com.artemissoftware.presentation.dogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.artemissoftware.common.Resource
import com.artemissoftware.domain.model.Dog
import com.artemissoftware.domain.usecase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(private val getDogsUseCase: GetDogsUseCase) : ViewModel(){


    private val _dogs = MutableLiveData<Resource<List<Dog>>>()
    val dogs: LiveData<Resource<List<Dog>>> = _dogs

    init {
        getDogs()
    }


    private fun getDogs() {

        _dogs.value = Resource.Loading()

        getDogsUseCase().onEach { result ->

            _dogs.value = result

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