package com.example.possumuschallenge.utils

import android.view.View
import androidx.core.view.isVisible
import com.example.possumuschallenge.data.dto.Album
import com.example.possumuschallenge.data.dto.Photo
import com.example.possumuschallenge.domain.model.AlbumModel
import com.example.possumuschallenge.domain.model.PhotoModel

fun Album.toModel(): AlbumModel {
    return AlbumModel(id = id, title = title)
}

fun Photo.toModel(): PhotoModel{
    return PhotoModel(
        id,
        albumId,
        title,
        url,
        thumbnailUrl
    )
}

fun View.setVisibility(isVisible: Boolean) {
    visibility = if(isVisible) View.VISIBLE else View.GONE
}