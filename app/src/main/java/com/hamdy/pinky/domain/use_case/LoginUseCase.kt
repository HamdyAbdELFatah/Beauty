package com.hamdy.pinky.domain.use_case

import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val product = repository.login(email, password)
            emit(Resource.Success<Boolean>(product))
        } catch (e: Exception) {
            emit(Resource.Error<Boolean>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
