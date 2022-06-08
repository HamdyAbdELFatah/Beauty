package com.hamdy.pinky.domain.repository

import com.hamdy.pinky.domain.model.FavoriteProduct
import com.hamdy.pinky.domain.model.Product

interface FavoriteItemsRepository {
    suspend fun addToFavoriteList(product: FavoriteProduct, currentUser: String): Boolean
    suspend fun getFavorite(productId: Int, currentUser: String): Boolean
    suspend fun removeFromFavoriteList(productId: Int, currentUser: String): Boolean
    suspend fun getAllFavorites(currentUser: String): List<FavoriteProduct>
}