package com.hamdy.pinky.domain.use_case.favorite_use_case

import com.hamdy.pinky.R
import com.hamdy.pinky.common.Constants.AN_UNEXPECTED_ERROR_OCCURRED
import com.hamdy.pinky.common.Constants.NO_INTERNET_CONNECTION
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.model.FavoriteProduct
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.domain.repository.FavoriteItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import java.sql.Timestamp
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val repository: FavoriteItemsRepository
) {
    operator fun invoke(
        product: Product,
        currentUser: String
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val favorite = FavoriteProduct(
                id = product.id,
                name = product.name,
                price = product.price,
                priceSign = product.priceSign ?: "$",
                imageLink = product.imageLink,
                timeStamp = Timestamp(System.currentTimeMillis()).toString(),
            )
            val favoriteProduct = repository.addToFavoriteList(favorite, currentUser)
            emit(Resource.Success<Boolean>(favoriteProduct))
        } catch (e: HttpException) {
            emit(
                Resource.Error<Boolean>(
                    e.localizedMessage ?: AN_UNEXPECTED_ERROR_OCCURRED
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<Boolean>(NO_INTERNET_CONNECTION))
        }
    }
}
