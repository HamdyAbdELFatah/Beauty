package com.hamdy.pinky.domain.model

import com.google.gson.annotations.SerializedName
import com.hamdy.pinky.data.remote.model.ProductColor

data class Product (
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    @SerializedName("price_sign")
    val priceSign: String,
    @SerializedName("image_link")
    val imageLink: String,
    @SerializedName("product_colors")
    val productColors: List<ProductColor>
)
