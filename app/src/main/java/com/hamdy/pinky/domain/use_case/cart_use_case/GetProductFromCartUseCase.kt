package com.hamdy.pinky.domain.use_case.cart_use_case

import com.hamdy.pinky.common.Constants
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.model.CartProduct
import com.hamdy.pinky.domain.repository.CartItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetProductFromCartUseCase  @Inject constructor(
    private val repository: CartItemsRepository
) {
    operator fun invoke(
        productId: String,
        currentUser: String,
    ): Flow<Resource<CartProduct?>> = flow {
        try {
            emit(Resource.Loading<CartProduct?>())
            val cartProduct = repository.getProductFromCart(productId, currentUser)
            emit(Resource.Success<CartProduct?>(cartProduct))
        } catch (e: HttpException) {
            emit(
                Resource.Error<CartProduct?>(
                    e.localizedMessage ?: Constants.AN_UNEXPECTED_ERROR_OCCURRED
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<CartProduct?>(Constants.NO_INTERNET_CONNECTION))
        }
    }
}
