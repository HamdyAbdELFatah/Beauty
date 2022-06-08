package com.hamdy.pinky.domain.use_case

import android.util.Log
import com.hamdy.pinky.common.Constants
import com.hamdy.pinky.common.Constants.AN_UNEXPECTED_ERROR_OCCURRED
import com.hamdy.pinky.common.Constants.NO_INTERNET_CONNECTION
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.domain.repository.MakeupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: MakeupRepository
) {
    operator fun invoke(category: String): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading<List<Product>>())
            val product = repository.getProducts(category)
            emit(Resource.Success<List<Product>>(product))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Product>>(e.localizedMessage ?: AN_UNEXPECTED_ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(Resource.Error<List<Product>>(NO_INTERNET_CONNECTION))
        }
    }
}
