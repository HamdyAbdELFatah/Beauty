package com.hamdy.pinky.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hamdy.pinky.common.Constants.FAVORITES_COLLECTIONS
import com.hamdy.pinky.common.Constants.USERS_COLLECTIONS
import com.hamdy.pinky.domain.model.FavoriteProduct
import com.hamdy.pinky.domain.repository.FavoriteItemsRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FavoriteItemsRepositoryImpl @Inject constructor(
) : FavoriteItemsRepository {

    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val collectionReference by lazy { db.collection(USERS_COLLECTIONS) }
    private val auth by lazy { FirebaseAuth.getInstance() }


    override suspend fun getFavorite(productId: Int, currentUser: String): Boolean {
        val document = collectionReference.document(currentUser)
            .collection(FAVORITES_COLLECTIONS).document(productId.toString()).get().await()

        return document.exists()
    }

    override suspend fun getAllFavorites(): List<FavoriteProduct>? {
        if (auth.currentUser == null) {
            return null
        }
        val arr = mutableListOf<FavoriteProduct>()
        val result = collectionReference.document(auth.currentUser?.uid.toString())
            .collection(FAVORITES_COLLECTIONS).get().await()
        for (i in result)
            arr.add(i.toObject(FavoriteProduct::class.java))
        return arr
    }

    override suspend fun addToFavoriteList(product: FavoriteProduct, currentUser: String): Boolean {
        val document = collectionReference.document(currentUser)
            .collection(FAVORITES_COLLECTIONS).document(product.id.toString())
        document.set(product).await()
        return true
    }

    override suspend fun removeFromFavoriteList(productId: Int, currentUser: String): Boolean {
        collectionReference.document(currentUser)
            .collection(FAVORITES_COLLECTIONS).document(productId.toString()).delete().await()
        return false
    }

}