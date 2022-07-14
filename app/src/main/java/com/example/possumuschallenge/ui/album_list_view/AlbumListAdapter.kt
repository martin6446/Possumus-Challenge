package com.example.possumuschallenge.ui.album_list_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.possumuschallenge.databinding.AlbumItemBinding
import com.example.possumuschallenge.domain.model.AlbumModel

class AlbumListAdapter(val onClickListener: (AlbumModel) -> Unit) :
    ListAdapter<AlbumModel, AlbumListAdapterViewHolder>(TaskDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListAdapterViewHolder {
        return AlbumListAdapterViewHolder(
            AlbumItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) {
            onClickListener(currentList[it])
        }
    }

    override fun onBindViewHolder(holder: AlbumListAdapterViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object TaskDiffCallBack : DiffUtil.ItemCallback<AlbumModel>() {
        override fun areItemsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            return oldItem == newItem
        }

    }

}

