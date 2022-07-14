package com.example.possumuschallenge.ui.album_list_view

import com.example.possumuschallenge.domain.model.AlbumModel
import com.example.possumuschallenge.domain.model.PhotoModel

data class AlbumsUiState(
    val isLoading: Boolean = false,
    val albums: List<AlbumModel> = listOf(),
    val photosByAlbum: List<PhotoModel> = listOf(),
)