package com.hamdy.pinky.presentation.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamdy.pinky.common.Constants.AN_UNEXPECTED_ERROR_OCCURRED
import com.hamdy.pinky.common.Constants.PARAM_CART_PRODUCT_ID
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.use_case.cart_use_case.GetAllProductsFromCartUseCase
import com.hamdy.pinky.domain.use_case.cart_use_case.RemoveFromCartUseCase
import com.hamdy.pinky.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CartScreenViewModel @Inject constructor(
    private val getAllProductsFromCartUseCase: GetAllProductsFromCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
) : ViewModel() {
    private var _cartScreenStateState = mutableStateOf(CartScreenState())
    val cartScreenStateState: State<CartScreenState> = _cartScreenStateState

    init {
        getAllProductsFromCart()
    }


    fun onEvent(event: CartScreenEvent) {
        val value = _cartScreenStateState.value
        when (event) {
            is CartScreenEvent.CartProductClicked -> {
                val product = value.products!![event.itemPosition]
                event.navController.navigate(
                    Screen.ProductDetails.route +
                            "/${product.id}?$PARAM_CART_PRODUCT_ID=${product.id}${product.productColor}"
                )
            }
            is CartScreenEvent.AddItemCount -> {
                val temp = value.products!![event.itemPosition]
                _cartScreenStateState.value = value.copy(
                    products = value.products.toMutableList().also {
                        it[event.itemPosition] = temp.copy(productCount = temp.productCount + 1)
                    }
                )
                //_cartScreenStateState.value = _cartScreenStateState.value

                calculateTotal()
            }
            is CartScreenEvent.ReduceItemCount -> {
                if (value.products != null && value.products[event.itemPosition].productCount > 0) {
                    val temp = value.products[event.itemPosition]
                    _cartScreenStateState.value = value.copy(
                        products = value.products.toMutableList().also {
                            it[event.itemPosition] = temp.copy(productCount = temp.productCount - 1)
                        }
                    )
                    //_cartScreenStateState.value = _cartScreenStateState.value
                    calculateTotal()
                }
            }
            is CartScreenEvent.CheckoutClicked -> {}
            is CartScreenEvent.SwipedToDeleted -> {
                if (value.products != null && value.products.isNotEmpty()) {
                    val product = value.products[event.itemPosition]
                    removeFromCart(productId = "${product.id}${product.productColor}")
                    _cartScreenStateState.value = value.copy(
                        products = value.products.toMutableList().also {
                            it.removeAt(event.itemPosition)
                        }
                    )
                    _cartScreenStateState.value = _cartScreenStateState.value
                    calculateTotal()
                }
            }
        }
    }

    private fun getAllProductsFromCart() {
        getAllProductsFromCartUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _cartScreenStateState.value =
                        _cartScreenStateState.value.copy(
                            products = result.data,
                            isLoading = false,
                        )
                    if (_cartScreenStateState.value.products != null)
                        calculateTotal()
                }
                is Resource.Error -> {
                    _cartScreenStateState.value = CartScreenState(
                        error = result.message ?: AN_UNEXPECTED_ERROR_OCCURRED,
                        isLoading = false,
                    )
                }
                is Resource.Loading -> {
                    _cartScreenStateState.value =
                        _cartScreenStateState.value.copy(
                            isLoading = true,
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun removeFromCart(productId: String) {
        removeFromCartUseCase(productId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _cartScreenStateState.value =
                        _cartScreenStateState.value.copy(
                            isLoading = false,
                        )
                }
                is Resource.Error -> {
                    _cartScreenStateState.value = CartScreenState(
                        error = result.message ?: AN_UNEXPECTED_ERROR_OCCURRED,
                        isLoading = false,
                    )
                }
                is Resource.Loading -> {
                    _cartScreenStateState.value =
                        _cartScreenStateState.value.copy(
                            isLoading = true,
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun calculateTotal() {
        var total = 0.0
        for (product in _cartScreenStateState.value.products!!) {
            total += (product.productCount * product.price.toDouble())
        }
        _cartScreenStateState.value = _cartScreenStateState.value.copy(
            subTotal = total,
            shipping = 10.0,
            total = total + 10
        )
    }
}