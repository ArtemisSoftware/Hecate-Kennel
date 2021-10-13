package com.artemissoftware.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.artemissoftware.presentation.MainDogActivity
import com.artemissoftware.presentation.R
import com.artemissoftware.presentation.databinding.FragmentDetailsBinding
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment(R.layout.fragment_details) {


    private val args by navArgs<DetailsFragmentArgs>()


    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as MainDogActivity).lolo(toolbar)

        _binding = FragmentDetailsBinding.bind(view)
        binding.dogDetail = args.dogDetail
    }
}