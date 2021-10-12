package com.artemissoftware.presentation.dogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemissoftware.presentation.R
import com.artemissoftware.presentation.databinding.FragmentDogsBinding
import com.artemissoftware.presentation.dogs.adapters.DogListAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.artemissoftware.common.Resource
import com.artemissoftware.domain.model.Dog
import com.artemissoftware.presentation.dogs.adapters.DogListener
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DogsFragment : Fragment(R.layout.fragment_dogs), DogListener{


    @Inject
    lateinit var glide: RequestManager

    private var _binding: FragmentDogsBinding? = null
    private val binding get() = _binding!!


    private val dogsViewModel: DogsViewModel by viewModels()
    private val listAdapter by lazy { DogListAdapter(glide, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        _binding = FragmentDogsBinding.bind(view)


        setupRecyclerView()
        initObservers()

    }


    private fun initObservers(){


        dogsViewModel.dogs.observe(viewLifecycleOwner) { result ->
            listAdapter.submitList(result.data)

            binding.progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
//            textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
//            textViewError.text = result.error?.localizedMessage
        }

    }


    private fun setupRecyclerView() {

        binding.recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
    }

    override fun onItemClick(dog: Dog) {

        val action = DogsFragmentDirections.actionDogsFragmentToDetailsFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}