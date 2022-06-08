package com.hamdy.pinky.presentation.product_details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamdy.pinky.common.Constants.AN_UNEXPECTED_ERROR_OCCURRED
import com.hamdy.pinky.common.Constants.PARAM_PRODUCT_ID
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.data.UserPreference
import com.hamdy.pinky.domain.model.FavoriteProduct
import com.hamdy.pinky.domain.use_case.GetProductDetailsUseCase
import com.hamdy.pinky.domain.use_case.favorite_use_case.AddToFavoriteUseCase
import com.hamdy.pinky.domain.use_case.favorite_use_case.GetFavoriteUseCase
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
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val userPreference: UserPreference,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _productDetailsState = mutableStateOf(ProductDetailsState())
    val productDetailsState: State<ProductDetailsState> = _productDetailsState


    init {
        getUser()
        savedStateHandle.get<String>(PARAM_PRODUCT_ID)?.let { productId ->
            getProductDetails(productId.toInt())
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            _productDetailsState.value =
                _productDetailsState.value.copy(userId = userPreference.getUser())
        }
    }

    private fun getProductDetails(id: Int) {
        val value = _productDetailsState.value
        getProductDetailsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _productDetailsState.value =
                        _productDetailsState.value.copy(
                            product = result.data,
                            isLoading = false,
                        )
                    getFavoriteItem()
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
                    if (value.isFavorite!!)
                        removeFavoriteList(value)
                    else
                        addToFavoriteList(value)
                }
            }
            is ProductDetailsEvent.AddItemCount -> {
                _productDetailsState.value = _productDetailsState.value.copy(
                    cartItemCount = value.cartItemCount + 1
                )
            }
            is ProductDetailsEvent.ReduceItemCount -> {
                val count = value.cartItemCount
                _productDetailsState.value = _productDetailsState.value.copy(
                    cartItemCount = if (count > 0) count - 1 else 0
                )
            }
            is ProductDetailsEvent.AddToCartClicked -> {
                if (value.userId != null) {
                    event.navController.navigate(Screen.Login.route)
                } else {
                    addToFavoriteList(value)
                }
            }
            is ProductDetailsEvent.ColorSelected -> {
                _productDetailsState.value = _productDetailsState.value.copy(
                    selectedColorPosition = event.colorPosition
                )
            }
        }
    }

    private fun addToFavoriteList(value: ProductDetailsState) {
        collector(
            addToFavoriteUseCase(
                product = value.product!!,
                currentUser = value.userId!!
            )
        )
    }

    private fun removeFavoriteList(value: ProductDetailsState) {
        collector(
            removeFromFavoriteUseCase(
                productId = value.product?.id!!,
                currentUser = value.userId!!
            )
        )
    }

    private fun getFavoriteItem() {
        collector(
            getFavoriteUseCase(
                productId = _productDetailsState.value.product?.id!!,
                currentUser = _productDetailsState.value.userId!!
            )
        )
    }

    private fun collector(receiver: Flow<Resource<Boolean>>) {
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
}