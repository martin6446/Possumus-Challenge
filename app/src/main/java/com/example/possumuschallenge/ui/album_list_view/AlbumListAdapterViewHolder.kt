package com.example.possumuschallenge.ui.album_list_view

import androidx.recyclerview.widget.RecyclerView
import com.example.possumuschallenge.databinding.AlbumItemBinding
import com.example.possumuschallenge.domain.model.AlbumModel

class AlbumListAdapterViewHolder(
    private val view: AlbumItemBinding,
    val onClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(view.root) {
    fun bind(albumItem: AlbumModel) {
        view.album = albumItem
    }

    init {
        view.root.setOnClickListener {
            onClickListener(adapterPosition)
        }
    }
}
