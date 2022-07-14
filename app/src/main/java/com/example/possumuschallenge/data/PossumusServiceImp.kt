package com.example.possumuschallenge.data

import com.example.possumuschallenge.data.dto.Album
import com.example.possumuschallenge.data.dto.Photo
import com.example.possumuschallenge.data.routes.Routes
import com.example.possumuschallenge.utils.Resource
import com.example.possumuschallenge.utils.Resource.Success
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PossumusServiceImp(
    private val client: HttpClient
) : PossumusService {

    override suspend fun getAlbums(): Flow<Resource<List<Album>>> = flow {
        var albums = listOf<Album>()
        try {
            albums = client.get {
                url(Routes.ALBUMS_URL)

                timeout {
                    requestTimeoutMillis = 5000
                }
            }.body()

        } catch (exception: HttpRequestTimeoutException) {
            emit(Resource.Error("timeout Error"))
        }

        emit(Success(albums))
    }.flowOn(Dispatchers.IO)

    override suspend fun getPhotos(albumId: Int?): Flow<Resource<List<Photo>>> = flow {
        var photos = listOf<Photo>()
        try {
            photos = client.get {
                url(Routes.PHOTOS_URL)

                albumId?.let { id ->
                    parameter("albumId", id)
                }
            }.body()
        } catch (exception: HttpRequestTimeoutException){
            emit(Resource.Error("timeout Error"))
        }

        emit(Success(photos))
    }.flowOn(Dispatchers.IO)
}