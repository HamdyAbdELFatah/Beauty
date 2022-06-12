package com.hamdy.pinky.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hamdy.pinky.common.Constants.CART_COLLECTIONS
import com.hamdy.pinky.common.Constants.FAVORITES_COLLECTIONS
import com.hamdy.pinky.common.Constants.USERS_COLLECTIONS
import com.hamdy.pinky.domain.model.CartProduct
import com.hamdy.pinky.domain.model.FavoriteProduct
import com.hamdy.pinky.domain.repository.CartItemsRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CartItemsRepositoryImpl @Inject constructor(
) : CartItemsRepository {

    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val collectionReference by lazy { db.collection(USERS_COLLECTIONS) }
    private val auth by lazy { FirebaseAuth.getInstance() }


    override suspend fun getAllProductsFromCart(): List<CartProduct>? {
        if (auth.currentUser == null) {
            return null
        }
        val arr = mutableListOf<CartProduct>()
        val result = collectionReference.document(auth.currentUser?.uid.toString())
            .collection(CART_COLLECTIONS).get().await()
        for (i in result)
            arr.add(i.toObject(CartProduct::class.java))
        return arr

    }

    override suspend fun getProductFromCart(
        productId: String,
        currentUser: String
    ): CartProduct? {
        val document = collectionReference.document(currentUser)
            .collection(CART_COLLECTIONS).document(productId).get()
            .await()
        return if (document.exists())
            document.toObject(CartProduct::class.java)
        else
            null
    }

    override suspend fun addToCartListList(product: CartProduct, currentUser: String): Boolean {
        val document = collectionReference.document(currentUser)
            .collection(CART_COLLECTIONS).document(product.id.toString() + product.productColor)
        document.set(product).await()
        return true
    }

    override suspend fun removeCartList(productId: String): Boolean {
        collectionReference.document(auth.currentUser?.uid.toString())
            .collection(CART_COLLECTIONS).document(productId).delete().await()
        return true
    }

}