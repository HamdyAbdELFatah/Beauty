package com.hamdy.pinky.data.remote

import com.hamdy.pinky.domain.model.Product
import retrofit2.http.GET

interface MakeupApi {

    @GET("product_category==lipstick")
    suspend fun getProducts(): List<Product>

}