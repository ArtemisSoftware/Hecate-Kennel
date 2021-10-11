package com.artemissoftware.presentation.dogs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artemissoftware.domain.model.Dog
import com.artemissoftware.presentation.databinding.ItemDogBinding
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DogListAdapter(private val glide: RequestManager, private val listener: DogListener) : ListAdapter<Dog, DogListAdapter.DogViewHolder>(DogDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {

        val bind = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(bind)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DogViewHolder(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.getRoot()) {

        init {
            binding.root.setOnClickListener {

                val position = adapterPosition

                if(position != RecyclerView.NO_POSITION){
                    listener.onItemClick(getItem(position))
                }
            }
        }

        fun bind(model: Dog) {
            with(binding) {
                item = model
                glide.load(model.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade(500))
                    .into(binding.imgDog)
                executePendingBindings()
            }
        }

    }


}