package com.example.possumuschallenge.ui.photo_list_view

import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.possumuschallenge.databinding.PhotoItemBinding
import com.example.possumuschallenge.domain.model.PhotoModel
import com.example.possumuschallenge.utils.setVisibility

class PhotoListAdapter : ListAdapter<PhotoModel, PhotoListViewHolder>(TaskDiffCallBack) {

    private var expandedItemPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        return PhotoListViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        val isExpanded = position == expandedItemPosition
        holder.apply {
            bind(currentList[position])
            binding.apply {
                thumbnail.setVisibility(isExpanded)
                mainImage.setVisibility(isExpanded)
            }

            itemView.isActivated = true
            itemView.setOnClickListener {
                expandedItemPosition = if (isExpanded) -1 else position
                TransitionManager.beginDelayedTransition(holder.binding.photoCard)
                notifyItemChanged(position)
            }
        }
    }
}

object TaskDiffCallBack : DiffUtil.ItemCallback<PhotoModel>() {
    override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
        return oldItem == newItem
    }

}