package com.example.possumuschallenge.ui.photo_list_view

import com.example.possumuschallenge.domain.model.PhotoModel

data class PhotosUiState(
    val isLoading: Boolean = false,
    val photos: List<PhotoModel> = listOf()
)