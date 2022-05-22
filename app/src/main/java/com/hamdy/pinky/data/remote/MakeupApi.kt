package com.hamdy.pinky.data.remote

import com.hamdy.pinky.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.Query

interface MakeupApi {

    @GET("products.json?")
    suspend fun getProducts(@Query("product_type") category: String): List<Product>

}