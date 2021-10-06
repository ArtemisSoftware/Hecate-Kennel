package com.artemissoftware.presentation.dogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.activity.viewModels
import android.view.View
import com.artemissoftware.presentation.R
import com.artemissoftware.presentation.dogs.adapters.DogListAdapter


class DogsFragment : Fragment(R.layout.fragment_dogs) {


    //--private val dogsViewModel: DogsViewModel by viewModels()
    private val listAdapter by lazy { DogListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}