package com.hamdy.pinky.domain.use_case.cart_use_case

import com.hamdy.pinky.common.Constants.AN_UNEXPECTED_ERROR_OCCURRED
import com.hamdy.pinky.common.Constants.NO_INTERNET_CONNECTION
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.model.CartProduct
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.domain.repository.CartItemsRepository
import com.hamdy.pinky.domain.repository.FavoriteItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import java.sql.Timestamp
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: CartItemsRepository
) {
    operator fun invoke(
        product: Product,
        productCount: Int,
        selectedColorPosition: Int,
        productColor: String,
        currentUser: String
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val cartItem = CartProduct(
                id = product.id,
                name = product.name,
                price = product.price,
                productCount = productCount,
                selectedColorPosition = selectedColorPosition,
                productColor = productColor,
                priceSign = product.priceSign,
                imageLink = product.imageLink,
                timeStamp = Timestamp(System.currentTimeMillis()).toString(),
            )
            val cartProduct = repository.addToCartListList(cartItem, currentUser)
            emit(Resource.Success<Boolean>(cartProduct))
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
