package com.hamdy.pinky.domain.model

data class CartProduct(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val productCount: Int = 0,
    val selectedColorPosition: Int = 0,
    val productColor: String = "",
    val priceSign: String = "",
    val imageLink: String = "",
    val timeStamp: String = "",
)
