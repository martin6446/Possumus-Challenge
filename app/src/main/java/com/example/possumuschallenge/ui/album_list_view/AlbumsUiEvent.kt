package com.example.possumuschallenge.ui.album_list_view

sealed class AlbumsUiEvent {
    object OnBackPressed : AlbumsUiEvent()
    data class OnAlbumSelected(val albumId: Int): AlbumsUiEvent()
}