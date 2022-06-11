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

class GetAllProductsFromCartUseCase @Inject constructor(
    private val repository: CartItemsRepository
) {
    operator fun invoke(
    ): Flow<Resource<List<CartProduct>?>> = flow {
        try {
            emit(Resource.Loading<List<CartProduct>?>())
            val cartProduct = repository.getAllProductsFromCart()
            emit(Resource.Success<List<CartProduct>?>(cartProduct))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<CartProduct>?>(
                    e.localizedMessage ?: Constants.AN_UNEXPECTED_ERROR_OCCURRED
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<CartProduct>?>(Constants.NO_INTERNET_CONNECTION))
        }
    }
}
