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

class AlbumsPhotoList: Fragment() {

    private lateinit var binding: FragmentItemListBinding
    private val viewModel: SharedViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)

        setupView()
        return binding.root
    }

    private fun setupView() {
        val photoListAdapter = PhotoListAdapter()
        viewModel.albumsUiState.observe(viewLifecycleOwner) {
            photoListAdapter.submitList(it.photosByAlbum)
            binding.itemList.adapter = photoListAdapter
        }
        binding.toolbar.apply {
            setVisibility(true)
            setNavigationOnClickListener {
                activity?.onBackPressed()
                setVisibility(false)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.toolbar.setVisibility(false)
    }
}