package com.hamdy.pinky.domain.repository

import com.hamdy.pinky.domain.model.Product

interface MakeupRepository {
    suspend fun getProducts(): List<Product>
}