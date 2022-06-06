package com.hamdy.pinky.domain.use_case

import com.google.firebase.auth.FirebaseUser
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(email: String, password: String): Flow<Resource<FirebaseUser?>> = flow {
        try {
            emit(Resource.Loading<FirebaseUser?>())
            val user = repository.login(email, password)
            emit(Resource.Success<FirebaseUser?>(user))
        } catch (e: Exception) {
            emit(Resource.Error<FirebaseUser?>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
