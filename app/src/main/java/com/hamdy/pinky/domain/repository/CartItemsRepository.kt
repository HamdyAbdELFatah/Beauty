package com.hamdy.pinky.domain.repository

import com.hamdy.pinky.domain.model.CartProduct
import com.hamdy.pinky.domain.model.FavoriteProduct

interface CartItemsRepository {
    suspend fun addToCartListList(product: CartProduct, currentUser: String): Boolean
    suspend fun removeCartList(productId: String): Boolean
    suspend fun getAllProductsFromCart(): List<CartProduct>?
    suspend fun getProductFromCart(productId: String, currentUser: String): CartProduct?
}