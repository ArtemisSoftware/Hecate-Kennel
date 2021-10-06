package com.artemissoftware.presentation.dogs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artemissoftware.domain.model.Dog
import com.artemissoftware.presentation.databinding.ItemDogBinding

class DogListAdapter() : ListAdapter<Dog, DogListAdapter.DogViewHolder>(DogDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {

        val bind = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(bind)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DogViewHolder(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.getRoot())/*, View.OnClickListener*/ {
//        override fun onClick(v: View?) {
//            when (v) {
//                itemView -> {
//
//                }
//            }
//        }
//
//        init {
//            itemView.radio_button.setOnClickListener(this)
//        }

        fun bind(model: Dog) {
            with(binding) {
                item = model
                executePendingBindings()
            }
        }

    }


}