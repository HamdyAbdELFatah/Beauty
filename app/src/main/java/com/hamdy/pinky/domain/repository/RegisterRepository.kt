package com.hamdy.pinky.domain.repository

import com.google.firebase.auth.FirebaseUser

interface RegisterRepository {

    suspend fun register(useName: String, email: String, password: String): FirebaseUser?

}