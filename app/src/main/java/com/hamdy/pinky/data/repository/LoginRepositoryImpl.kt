package com.hamdy.pinky.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hamdy.pinky.domain.repository.LoginRepository
import kotlinx.coroutines.tasks.await

class LoginRepositoryImpl : LoginRepository {

    override suspend fun login(email: String, password: String): FirebaseUser? {
        val result =
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return result.user

    }
}
