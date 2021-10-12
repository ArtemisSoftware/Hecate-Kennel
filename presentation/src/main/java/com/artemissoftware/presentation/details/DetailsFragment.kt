package com.artemissoftware.presentation.details

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artemissoftware.presentation.MainDogActivity
import com.artemissoftware.presentation.R
import kotlinx.android.synthetic.main.activity_dog_main.*
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.toolbar


class DetailsFragment : Fragment(R.layout.fragment_details) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as MainDogActivity).toolbar.visibility = View.INVISIBLE
    }
}