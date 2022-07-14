package com.example.possumuschallenge.ui.photo_list_view

import androidx.recyclerview.widget.RecyclerView
import com.example.possumuschallenge.databinding.PhotoItemBinding
import com.example.possumuschallenge.domain.model.PhotoModel

class PhotoListViewHolder(val binding: PhotoItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(photoModel: PhotoModel) {
        binding.photo = photoModel
    }

}