package com.hamdy.pinky.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.hamdy.pinky.common.Constants.USERS_COLLECTIONS
import com.hamdy.pinky.common.Constants.USER_ID_FIELD
import com.hamdy.pinky.common.Constants.USER_NAME_FIELD
import com.hamdy.pinky.domain.repository.RegisterRepository
import kotlinx.coroutines.tasks.await

class RegisterRepositoryImpl : RegisterRepository {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    override suspend fun register(useName: String, email: String, password: String): FirebaseUser? {
        val result =
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        result.user?.let { user ->
            val collectionReference = db.collection(USERS_COLLECTIONS).document(user.uid)
            val map = HashMap<String, String>()
            map[USER_NAME_FIELD] = useName
            map[USER_ID_FIELD] = user.uid
            collectionReference.set(map).await()
        }
        return result.user
    }
}