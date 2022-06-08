package com.hamdy.pinky.presentation.product_details

import com.hamdy.pinky.domain.model.Product

data class ProductDetailsState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String = "",
    val userId: String? = "",
    val selectedColorPosition: Int = -1,
    val selectedColorPositionError: Int? = null,
    val cartItemCount: Int = 0,
    val cartItemCountError: Int? = null,
    val isFavorite: Boolean? = false
)