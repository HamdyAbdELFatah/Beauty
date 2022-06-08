package com.hamdy.pinky.domain.model

import com.google.gson.annotations.SerializedName
import com.hamdy.pinky.data.remote.model.ProductColor

data class FavoriteProduct (
    val id: Int,
    val name: String,
    val price: String,
    val priceSign: String,
    val imageLink: String,
    val timeStamp: String,
)
