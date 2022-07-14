package com.example.possumuschallenge.domain.model

data class PhotoModel(
    val id: Int = 0,
    val albumId: Int = 0,
    val title: String = "",
    val url: String = "",
    val thumbnailUrl: String = "",
    var isExpanded: Boolean = false
)