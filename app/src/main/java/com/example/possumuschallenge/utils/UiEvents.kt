package com.example.possumuschallenge.utils

import androidx.navigation.NavDirections

sealed class UiEvents {
    data class Navigate(val destination: NavDirections) : UiEvents()
    data class ShowSnackBar(
        val message: String = "There was an error loading the data",
        val action: String = "try again",
        val onClickListener: () -> Unit
    ) : UiEvents()
}