package com.example.possumuschallenge.data

import com.example.possumuschallenge.data.dto.Album
import com.example.possumuschallenge.data.dto.Photo
import kotlinx.coroutines.flow.Flow

interface PossumusService {
    suspend fun getAlbums(): Flow<List<Album>>
    suspend fun getPhotos(albumId: Int? = null): Flow<List<Photo>>
}