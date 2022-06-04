package com.hamdy.pinky.domain.repository

interface LoginRepository {

    suspend fun login(email: String, password: String): Boolean

}