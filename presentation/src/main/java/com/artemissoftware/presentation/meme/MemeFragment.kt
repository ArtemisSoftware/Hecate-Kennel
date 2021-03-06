package com.artemissoftware.presentation.meme

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.artemissoftware.common.Resource
import com.artemissoftware.presentation.R
import com.artemissoftware.presentation.databinding.FragmentMemeBinding
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MemeFragment : Fragment(R.layout.fragment_meme) {


    @Inject
    lateinit var glide: RequestManager


    private var _binding: FragmentMemeBinding? = null
    private val binding get() = _binding!!


    private val viewModel: MemeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        _binding = FragmentMemeBinding.bind(view)

        initObservers()
    }


    private fun initObservers(){


        viewModel.meme.observe(viewLifecycleOwner) { result ->

            glide.load(result.data?.url).into(binding.imgMeme);

            binding.progressBar.isVisible = result is Resource.Loading
            setError(result is Resource.Error, result.message)
//            textViewError.text = result.error?.localizedMessage
        }

    }


    private fun setError(visible: Boolean, message: String?){

        if(visible) {
            Snackbar.make(requireView(), message!!, Snackbar.LENGTH_LONG).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}