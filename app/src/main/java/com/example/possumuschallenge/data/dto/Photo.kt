package com.example.possumuschallenge.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
