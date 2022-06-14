package com.hamdy.pinky.domain.use_case.favorite_use_case

import com.hamdy.pinky.common.Constants
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.model.FavoriteProduct
import com.hamdy.pinky.domain.repository.FavoriteItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
    private val repository: FavoriteItemsRepository
) {
    operator fun invoke(): Flow<Resource<List<FavoriteProduct>?>> = flow {
        try {
            emit(Resource.Loading<List<FavoriteProduct>?>())
            val favoriteProduct = repository.getAllFavorites()
            emit(Resource.Success<List<FavoriteProduct>?>(favoriteProduct))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<FavoriteProduct>?>(
                    e.localizedMessage ?: Constants.AN_UNEXPECTED_ERROR_OCCURRED
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<FavoriteProduct>?>(Constants.NO_INTERNET_CONNECTION))
        }
    }
}
