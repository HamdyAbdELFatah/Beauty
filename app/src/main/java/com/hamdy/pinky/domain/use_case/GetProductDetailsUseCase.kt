package com.hamdy.pinky.domain.use_case

import android.util.Log
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.domain.repository.MakeupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val repository: MakeupRepository
) {
    operator fun invoke(productId: Int): Flow<Resource<Product>> = flow {
        try {
            emit(Resource.Loading())
            val product = repository.getProduct(productId)
            emit(Resource.Success(product))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.e("TAG", "invoke: $e")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}