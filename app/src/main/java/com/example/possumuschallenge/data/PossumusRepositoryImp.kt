package com.example.possumuschallenge.data

import com.example.possumuschallenge.data.dto.Album
import com.example.possumuschallenge.data.dto.Photo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class PossumusRepositoryImp (
    private val service: PossumusService
) : PossumusRepository {
    override suspend fun getAlbums(): Flow<List<Album>> {
        //This delay is just to show the loading feature
        delay(500)
        return service.getAlbums()
    }

    override suspend fun getPhotos(albumId: Int?): Flow<List<Photo>> {
        return service.getPhotos(albumId)
    }
}