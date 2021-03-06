package com.example.possumuschallenge.data

import com.example.possumuschallenge.data.dto.Album
import com.example.possumuschallenge.data.dto.Photo
import com.example.possumuschallenge.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class PossumusRepositoryImp (
    private val service: PossumusService
) : PossumusRepository {
    override suspend fun getAlbums(): Flow<Resource<List<Album>>> {
        return service.getAlbums()
    }

    override suspend fun getPhotos(albumId: Int?): Flow<Resource<List<Photo>>> {
        return service.getPhotos(albumId)
    }
}