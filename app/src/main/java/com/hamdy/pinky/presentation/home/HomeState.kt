package com.hamdy.pinky.presentation.home

import com.hamdy.pinky.domain.model.Product

data class HomeState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String = ""
)