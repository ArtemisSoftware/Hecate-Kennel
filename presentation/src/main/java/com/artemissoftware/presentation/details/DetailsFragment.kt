package com.artemissoftware.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.artemissoftware.presentation.MainDogActivity
import com.artemissoftware.presentation.R
import com.artemissoftware.presentation.databinding.FragmentDetailsBinding
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_details.*

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {


    private val args by navArgs<DetailsFragmentArgs>()

    @Inject
    lateinit var glide: RequestManager


    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as MainDogActivity).updateToolbar(toolbar)

        _binding = FragmentDetailsBinding.bind(view)
        binding.dogDetail = args.dogDetail

        glide.load(args.dogDetail.imageUrl).into(binding.imgBanner);

    }
}