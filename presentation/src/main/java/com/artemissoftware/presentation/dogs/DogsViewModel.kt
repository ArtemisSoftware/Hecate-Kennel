package com.artemissoftware.presentation.dogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.artemissoftware.common.Resource
import com.artemissoftware.domain.errors.DataException
import com.artemissoftware.domain.model.Dog
import com.artemissoftware.domain.usecase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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

        viewModelScope.launch {

            try {

                getDogsUseCase.invoke().collect { result ->
                    _dogs.value = result
                }

            } catch (ex: Exception) {
                when (ex) {
                    is DataException ->{
                        _dogs.value = Resource.Error("Couldn't find an image")
                    }
                    else -> {
                        _dogs.value = Resource.Error(ex.message?: "")
                    }
                }
            }
        }

    }
}