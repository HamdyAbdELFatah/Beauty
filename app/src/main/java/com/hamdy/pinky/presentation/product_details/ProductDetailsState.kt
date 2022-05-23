package com.hamdy.pinky.presentation.product_details

import com.hamdy.pinky.domain.model.Product

data class ProductDetailsState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String = ""
)