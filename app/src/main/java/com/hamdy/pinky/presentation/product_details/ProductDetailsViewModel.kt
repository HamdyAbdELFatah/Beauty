package com.hamdy.pinky.presentation.product_details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.hamdy.pinky.common.Constants.AN_UNEXPECTED_ERROR_OCCURRED
import com.hamdy.pinky.common.Constants.PARAM_CART_PRODUCT_ID
import com.hamdy.pinky.common.Constants.PARAM_Favorite_PRODUCT_ID
import com.hamdy.pinky.common.Constants.PARAM_PRODUCT_ID
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.data.UserPreference
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.domain.use_case.GetProductDetailsUseCase
import com.hamdy.pinky.domain.use_case.cart_use_case.AddToCartUseCase
import com.hamdy.pinky.domain.use_case.cart_use_case.GetProductFromCartUseCase
import com.hamdy.pinky.domain.use_case.favorite_use_case.AddToFavoriteUseCase
import com.hamdy.pinky.domain.use_case.favorite_use_case.GetFavoriteProductUseCase
import com.hamdy.pinky.domain.use_case.favorite_use_case.RemoveFromFavoriteUseCase
import com.hamdy.pinky.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val removeFromFavoriteUseCase: RemoveFromFavoriteUseCase,
    private val getFavoriteProductUseCase: GetFavoriteProductUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val getProductFromCartUseCase: GetProductFromCartUseCase,
    private val userPreference: UserPreference,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _productDetailsState = mutableStateOf(ProductDetailsState())
    val productDetailsState: State<ProductDetailsState> = _productDetailsState


    init {
        getUser()
        savedStateHandle.get<Int>(PARAM_PRODUCT_ID)?.let { productId ->
            getProductDetails(productId)
        }

    }

    private fun getUser() {
        viewModelScope.launch {
            _productDetailsState.value =
                _productDetailsState.value.copy(userId = userPreference.getUser())
        }
    }

    private fun getProductDetails(id: Int) {
        getProductDetailsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _productDetailsState.value =
                        _productDetailsState.value.copy(
                            product = result.data,
                            isLoading = false,
                        )
                    getFavoriteItem()
                    savedStateHandle.get<String>(PARAM_CART_PRODUCT_ID)?.let { productId ->
                        getProductFromCart(_productDetailsState.value, productId)
                    }
                }
                is Resource.Error -> {
                    _productDetailsState.value = ProductDetailsState(
                        error = result.message ?: AN_UNEXPECTED_ERROR_OCCURRED,
                        isLoading = false,
                    )
                }
                is Resource.Loading -> {
                    _productDetailsState.value =
                        _productDetailsState.value.copy(
                            isLoading = true,
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ProductDetailsEvent) {
        val value = _productDetailsState.value
        when (event) {
            is ProductDetailsEvent.FavoriteButtonClicked -> {
                if (value.userId == null) {
                    event.navController.navigate(Screen.Login.route)
                } else {
                    if (value.isFavorite!!) {
                        event.navController.previousBackStackEntry?.savedStateHandle?.set(
                            PARAM_Favorite_PRODUCT_ID,
                            value.product?.id ?: -1
                        )
                        removeFavoriteList(value)
                    } else {
                        addToFavoriteList(value)
                        event.navController.previousBackStackEntry?.savedStateHandle?.set(
                            PARAM_Favorite_PRODUCT_ID,
                            -1
                        )
                    }
                }
            }
            is ProductDetailsEvent.AddItemCount -> {
                _productDetailsState.value = value.copy(
                    cartItemCount = value.cartItemCount + 1,
                    cartItemCountError = null
                )
            }
            is ProductDetailsEvent.ReduceItemCount -> {
                val count = value.cartItemCount
                _productDetailsState.value = value.copy(
                    cartItemCount = if (count > 0) count - 1 else 0
                )
            }
            is ProductDetailsEvent.AddToCartClicked -> {
                if (value.userId == null) {
                    event.navController.navigate(Screen.Login.route)
                } else if (value.selectedColorPosition < 0 || value.cartItemCount == 0) {
                    if (value.selectedColorPosition < 0)
                        _productDetailsState.value = value.copy(
                            selectedColorPositionError = ResString.You_should_select_color
                        )
                    if (value.cartItemCount == 0)
                        _productDetailsState.value = _productDetailsState.value.copy(
                            cartItemCountError = ResString.count_could_not_be_zero
                        )

                } else
                    addToCartList(value, event.navController)
            }
            is ProductDetailsEvent.ColorSelected -> {
                _productDetailsState.value = value.copy(
                    selectedColorPosition = event.colorPosition,
                    selectedColorPositionError = null
                )
            }
        }
    }

    private fun addToFavoriteList(value: ProductDetailsState) {
        favoriteCollector(
            addToFavoriteUseCase(
                product = value.product!!,
                currentUser = value.userId!!
            )
        )
    }

    private fun removeFavoriteList(value: ProductDetailsState) {
        favoriteCollector(
            removeFromFavoriteUseCase(
                productId = value.product?.id!!,
                currentUser = value.userId!!
            )
        )
    }

    private fun addToCartList(value: ProductDetailsState, navController: NavHostController) {
        cartCollector(
            addToCartUseCase(
                product = value.product!!,
                currentUser = value.userId!!,
                productCount = value.cartItemCount,
                selectedColorPosition = value.selectedColorPosition,
                productColor = value.product.productColors[value.selectedColorPosition].colourName
            ), navController
        )
    }

    private fun getFavoriteItem() {
        favoriteCollector(
            getFavoriteProductUseCase(
                productId = _productDetailsState.value.product?.id!!,
                currentUser = _productDetailsState.value.userId!!
            )
        )
    }

    private fun getProductFromCart(value: ProductDetailsState, productId: String) {
        getProductFromCartUseCase(
            productId = productId,
            currentUser = value.userId!!,
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _productDetailsState.value = _productDetailsState.value.copy(
                        isLoading = false,
                    )
                    Log.d("LastMan", "getProductFromCart: $productId")
                    if (result.data != null) {
                        _productDetailsState.value = _productDetailsState.value.copy(
                            selectedColorPosition = result.data.selectedColorPosition,
                            cartItemCount = result.data.productCount
                        )
                        Log.d("LastMan", "getProductFromCart: ${result.data.name}")
                    }
                }
                is Resource.Error -> {
                    _productDetailsState.value = _productDetailsState.value.copy(
                        error = result.message ?: AN_UNEXPECTED_ERROR_OCCURRED,
                        isLoading = false,
                    )
                }
                is Resource.Loading -> {
                    _productDetailsState.value =
                        _productDetailsState.value.copy(
                            isLoading = true,
                        )
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun favoriteCollector(receiver: Flow<Resource<Boolean>>) {
        receiver.onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _productDetailsState.value =
                        _productDetailsState.value.copy(
                            isFavorite = result.data,
                            isLoading = false,

                            )
                }
                is Resource.Error -> {
                    _productDetailsState.value = _productDetailsState.value.copy(
                        error = result.message ?: AN_UNEXPECTED_ERROR_OCCURRED,
                        isLoading = false,
                    )
                }
                is Resource.Loading -> {
                    _productDetailsState.value =
                        _productDetailsState.value.copy(
                            isLoading = true,
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun cartCollector(receiver: Flow<Resource<Boolean>>, navController: NavHostController) {
        receiver.onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _productDetailsState.value =
                        _productDetailsState.value.copy(
                            isLoading = false,
                        )
                    navController.popBackStack()
                }
                is Resource.Error -> {
                    _productDetailsState.value = _productDetailsState.value.copy(
                        error = result.message ?: AN_UNEXPECTED_ERROR_OCCURRED,
                        isLoading = false,
                    )
                }
                is Resource.Loading -> {
                    _productDetailsState.value =
                        _productDetailsState.value.copy(
                            isLoading = true,
                            selectedColorPositionError = null,
                            cartItemCountError = null
                        )
                }
            }
        }.launchIn(viewModelScope)
    }
}