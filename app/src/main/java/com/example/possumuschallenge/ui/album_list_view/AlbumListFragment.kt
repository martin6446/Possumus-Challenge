package com.example.possumuschallenge.ui.album_list_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.possumuschallenge.databinding.FragmentItemListBinding
import com.example.possumuschallenge.ui.SharedViewModel
import com.example.possumuschallenge.ui.photo_list_view.PhotoListAdapter
import com.example.possumuschallenge.utils.setVisibility
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AlbumListFragment : Fragment() {

    private val viewModel: SharedViewModel by sharedViewModel()
    private lateinit var binding: FragmentItemListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(layoutInflater, container, false)

        setUpView()
        return binding.root
    }

    private fun setUpView() {
        viewModel.getAlbums()

        val albumListAdapter = AlbumListAdapter {
            viewModel.onAlbumsUiEvent(AlbumsUiEvent.OnAlbumSelected(it.id))
        }

        val photoListAdapter = PhotoListAdapter()

        viewModel.albumsUiState.observe(viewLifecycleOwner) {
            showLoading(it.isLoading)
            albumListAdapter.submitList(it.albums)
            photoListAdapter.submitList(it.photosByAlbum)
            binding.itemList.adapter =
                if (it.albums.isEmpty()) photoListAdapter else albumListAdapter
            showPhotosByAlbum(it.photosByAlbum.isNotEmpty())
        }
    }

    private fun showPhotosByAlbum(hasPhotos: Boolean) {
        binding.toolbar.takeIf {
            hasPhotos
        }?.apply {
            setVisibility(hasPhotos)
            setNavigationOnClickListener {
                viewModel.onAlbumsUiEvent(AlbumsUiEvent.OnBackPressed)
                setVisibility(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loadingIndicator.setVisibility(isLoading)
    }
}


