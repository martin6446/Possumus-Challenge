package com.example.possumuschallenge.data

import com.example.possumuschallenge.data.dto.Album
import com.example.possumuschallenge.data.dto.Photo
import com.example.possumuschallenge.data.routes.Routes
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn

class PossumusServiceImp(
    private val client: HttpClient
) : PossumusService {

    override suspend fun getAlbums(): Flow<List<Album>> = flow {
        val albums: List<Album> = client.get {
            url(Routes.ALBUMS_URL)
        }.body()

        emit(albums)
    }.flowOn(Dispatchers.IO)

    override suspend fun getPhotos(albumId: Int?): Flow<List<Photo>> = flow{
        val photos: List<Photo> = client.get {
            url(Routes.PHOTOS_URL)

            albumId?.let { id ->
                parameter("albumId",id)
            }
        }.body()

        emit(photos)
    }.flowOn(Dispatchers.IO)
}