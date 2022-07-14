package com.example.possumuschallenge.utils

import androidx.navigation.NavDirections

sealed class UiEvents{
    data class Navigate(val destiny: NavDirections): UiEvents()
}