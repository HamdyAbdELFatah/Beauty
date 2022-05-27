package com.hamdy.pinky.data.remote

import com.hamdy.pinky.domain.model.Product
import retrofit2.http.*

interface MakeupApi {

    @GET("products.json?")
    suspend fun getProducts(@Query("product_type") category: String): List<Product>

    @GET("products/{product_id}.json?")
    suspend fun getProduct(@Path("product_id") productId: Int): Product

}