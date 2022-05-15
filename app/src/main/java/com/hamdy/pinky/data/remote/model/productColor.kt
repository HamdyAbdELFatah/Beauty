package com.hamdy.pinky.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductColor(
    @SerializedName("hex_value")
    val hexValue: String,
    @SerializedName("colour_name")
    val colourName: String
)


