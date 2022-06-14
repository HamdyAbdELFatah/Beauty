package com.hamdy.pinky.presentation.favorite

import com.hamdy.pinky.domain.model.FavoriteProduct

data class FavoriteScreenState(
    val isLoading: Boolean = false,
    val products: List<FavoriteProduct>? = null,
    val error: String = "",
    val imageBackgroundUrl: String = "",
    val currentItem: Int = 0,
)

