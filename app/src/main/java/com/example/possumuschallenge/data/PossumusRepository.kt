package com.example.possumuschallenge.data

import com.example.possumuschallenge.data.dto.Album
import com.example.possumuschallenge.data.dto.Photo
import com.example.possumuschallenge.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PossumusRepository {

    suspend fun getAlbums(): Flow<Resource<List<Album>>>

    suspend fun getPhotos(albumId: Int? = null): Flow<Resource<List<Photo>>>
}