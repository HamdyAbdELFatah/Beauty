package com.hamdy.pinky.domain.use_case.cart_use_case

import com.hamdy.pinky.common.Constants
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.repository.CartItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class RemoveFromCartUseCase  @Inject constructor(
    private val repository: CartItemsRepository
) {
    operator fun invoke(
        productId: Int
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val cartProduct = repository.removeCartList(productId)
            emit(Resource.Success<Boolean>(cartProduct))
        } catch (e: HttpException) {
            emit(
                Resource.Error<Boolean>(
                    e.localizedMessage ?: Constants.AN_UNEXPECTED_ERROR_OCCURRED
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<Boolean>(Constants.NO_INTERNET_CONNECTION))
        }
    }
}
