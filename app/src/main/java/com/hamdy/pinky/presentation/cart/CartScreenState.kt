package com.hamdy.pinky.presentation.cart

import com.hamdy.pinky.domain.model.CartProduct

data class CartScreenState(
    val isLoading: Boolean = false,
    val products: List<CartProduct>? = null,
    val error: String = "",
    val cartItemCount: Int = 0,
    val cartItemCountError: Int? = null,
    val subTotal: Double = 0.0,
    val shipping: Double = 0.0,
    val total: Double = 0.0,
)

