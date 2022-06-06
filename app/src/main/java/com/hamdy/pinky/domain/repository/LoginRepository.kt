package com.hamdy.pinky.domain.repository

import com.google.firebase.auth.FirebaseUser

interface LoginRepository {

    suspend fun login(email: String, password: String): FirebaseUser?

}