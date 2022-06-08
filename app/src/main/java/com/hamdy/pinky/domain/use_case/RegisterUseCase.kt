package com.hamdy.pinky.domain.use_case

import com.google.firebase.auth.FirebaseUser
import com.hamdy.pinky.common.Constants.AN_UNEXPECTED_ERROR_OCCURRED
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.repository.LoginRepository
import com.hamdy.pinky.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    operator fun invoke(
        userName: String,
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser?>> = flow {
        try {
            emit(Resource.Loading<FirebaseUser?>())
            val user = repository.register(userName, email, password)
            emit(Resource.Success<FirebaseUser?>(user))
        } catch (e: Exception) {
            emit(
                Resource.Error<FirebaseUser?>(
                    e.localizedMessage ?: AN_UNEXPECTED_ERROR_OCCURRED
                )
            )
        }
    }
}
