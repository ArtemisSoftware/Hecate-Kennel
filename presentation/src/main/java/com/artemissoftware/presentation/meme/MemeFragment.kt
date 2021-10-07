package com.artemissoftware.presentation.meme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.artemissoftware.common.Resource
import com.artemissoftware.presentation.R
import com.artemissoftware.presentation.dogs.DogsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemeFragment : Fragment(R.layout.fragment_meme) {


    private val viewModel: MemeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //--Glide.with(context).load(GIF_URI).into(new GlideDrawableImageViewTarget(IMAGE_VIEW));

        initObservers()
    }


    private fun initObservers(){


        viewModel.meme.observe(viewLifecycleOwner) { result ->

            //binding.progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
//            textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
//            textViewError.text = result.error?.localizedMessage
        }

    }
}