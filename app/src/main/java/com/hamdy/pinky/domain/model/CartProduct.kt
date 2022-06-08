package com.hamdy.pinky.domain.model

data class CartProduct (
    val id: Int,
    val name: String,
    val price: String,
    val productCount: Int,
    val selectedColorPosition: Int,
    val productColor: String,
    val priceSign: String,
    val imageLink: String,
    val timeStamp: String,
)
