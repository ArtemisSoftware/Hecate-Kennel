package com.artemissoftware.presentation.pet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artemissoftware.presentation.R
import com.artemissoftware.presentation.databinding.FragmentMemeBinding
import com.artemissoftware.presentation.databinding.FragmentPetBinding


class PetFragment : Fragment(R.layout.fragment_pet) {

    private var _binding: FragmentPetBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        _binding = FragmentPetBinding.bind(view)
        initOnClicklistener()
    }

    private fun initOnClicklistener(){

        binding.imgPet.setOnClickListener {

        }
    }

}