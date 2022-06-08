package com.hamdy.pinky.domain.use_case.cart_use_case

import com.hamdy.pinky.common.Constants
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.repository.FavoriteItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetAllProductsFromCartUseCase  @Inject constructor(
    private val repository: FavoriteItemsRepository
) {
    operator fun invoke(
        productId: Int,
        currentUser: String
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val favoriteProduct = repository.removeFromFavoriteList(productId, currentUser)
            emit(Resource.Success<Boolean>(favoriteProduct))
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
