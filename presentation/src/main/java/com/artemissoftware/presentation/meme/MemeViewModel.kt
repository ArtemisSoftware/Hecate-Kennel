package com.artemissoftware.presentation.meme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.common.Resource
import com.artemissoftware.domain.errors.DataException
import com.artemissoftware.domain.model.Meme
import com.artemissoftware.domain.usecase.GetMemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemeViewModel @Inject constructor(private val getMemeUseCase: GetMemeUseCase) : ViewModel(){

    private val _meme = MutableLiveData<Resource<Meme>>()
    val meme: LiveData<Resource<Meme>> = _meme

    init {
        getMeme()
    }


    private fun getMeme() {

        _meme.value = Resource.Loading()

        viewModelScope.launch {

            try {

                getMemeUseCase.invoke().collect { result ->
                    _meme.value = result
                }

            } catch (ex: Exception) {
                when (ex) {
                    is DataException ->{
                        _meme.value = Resource.Error("Couldn't find an image")
                    }
                    else -> {
                        val dd = 0
                    }
                }
            }
        }

    }
}