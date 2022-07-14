package com.example.possumuschallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.possumuschallenge.data.PossumusRepository
import com.example.possumuschallenge.ui.album_list_view.AlbumListFragmentDirections
import com.example.possumuschallenge.ui.album_list_view.AlbumsUiEvent
import com.example.possumuschallenge.ui.album_list_view.AlbumsUiState
import com.example.possumuschallenge.ui.photo_list_view.PhotosUiState
import com.example.possumuschallenge.utils.UiEvents
import com.example.possumuschallenge.utils.toModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class SharedViewModel(
    private val repository: PossumusRepository
) : ViewModel() {
    private val _albumsUiState = MutableLiveData(AlbumsUiState())
    val albumsUiState: LiveData<AlbumsUiState>
        get() = _albumsUiState

    private val _photosUiState = MutableLiveData(PhotosUiState())
    val photosUiState: LiveData<PhotosUiState>
        get() = _photosUiState

    private val _uiEvents = Channel<UiEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    fun onAlbumsUiEvent(event: AlbumsUiEvent) = when (event) {
        is AlbumsUiEvent.OnAlbumSelected -> {
            getPhotosByAlbum(event.albumId)
            sendEvent(UiEvents.Navigate(AlbumListFragmentDirections.actionAlbumsToAlbumsPhotoList()))
        }

        AlbumsUiEvent.OnBackPressed -> getAlbums()
    }

    private fun getPhotosByAlbum(albumId: Int) {
        viewModelScope.launch {
            _albumsUiState.value = AlbumsUiState(true)

            repository.getPhotos(albumId).collect { photos ->
                _albumsUiState.postValue(AlbumsUiState(photosByAlbum = photos.map { it.toModel() }))
            }
        }
    }

    fun getPhotos() {
        _photosUiState.value?.let { uiState ->
            uiState.photos.ifEmpty {
                viewModelScope.launch {
                    _photosUiState.value = PhotosUiState(isLoading = true)

                    repository.getPhotos().collect { photoList ->
                        _photosUiState.postValue(PhotosUiState(photos = photoList.map { it.toModel() }))
                    }
                }
            }
        }
    }

    fun getAlbums() {
        _albumsUiState.value?.let { uiState ->
            uiState.albums.ifEmpty {
                viewModelScope.launch {
                    _albumsUiState.value = AlbumsUiState(isLoading = true)

                    repository.getAlbums().collect { albumList ->
                        _albumsUiState.postValue(AlbumsUiState(albums = albumList.map { it.toModel() }))
                    }
                }
            }
        }
    }

    private fun sendEvent(event: UiEvents){
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }
}