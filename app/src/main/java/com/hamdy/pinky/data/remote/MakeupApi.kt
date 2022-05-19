package com.hamdy.pinky.data.remote

import com.hamdy.pinky.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface MakeupApi {

    @GET("products.json?product_category=lipstick")
    suspend fun getProducts(): List<Product>

}