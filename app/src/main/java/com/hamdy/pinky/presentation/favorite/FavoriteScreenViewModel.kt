package com.hamdy.pinky.presentation.favorite

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamdy.pinky.common.Constants
import com.hamdy.pinky.common.Constants.AN_UNEXPECTED_ERROR_OCCURRED
import com.hamdy.pinky.common.Constants.PARAM_CART_PRODUCT_ID
import com.hamdy.pinky.common.Constants.PARAM_Favorite_PRODUCT_ID
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.use_case.favorite_use_case.GetAllFavoritesUseCase
import com.hamdy.pinky.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _favoriteScreenStateState = mutableStateOf(FavoriteScreenState())
    val favoriteScreenStateState: State<FavoriteScreenState> = _favoriteScreenStateState

    init {
        getAllProductsFromFavorite()
    }

    fun onEvent(event: FavoriteScreenEvent) {
        val value = _favoriteScreenStateState.value
        when (event) {
            is FavoriteScreenEvent.CartProductClicked -> {
                val product = value.products!![event.itemPosition]
                event.navController.navigate(
                    Screen.ProductDetails.route + "/${product.id}"
                )
                _favoriteScreenStateState.value =
                    _favoriteScreenStateState.value.copy(
                        currentItem = event.itemPosition,
                    )
            }
            is FavoriteScreenEvent.ChangeBackgroundImage -> {
                _favoriteScreenStateState.value =
                    _favoriteScreenStateState.value.copy(
                        imageBackgroundUrl = event.url,
                    )
            }
        }
    }

    private fun getAllProductsFromFavorite() {
        getAllFavoritesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _favoriteScreenStateState.value =
                        _favoriteScreenStateState.value.copy(
                            products = result.data,
                            isLoading = false,
                        )
                }
                is Resource.Error -> {
                    _favoriteScreenStateState.value = FavoriteScreenState(
                        error = result.message ?: AN_UNEXPECTED_ERROR_OCCURRED,
                        isLoading = false,
                    )
                }
                is Resource.Loading -> {
                    _favoriteScreenStateState.value =
                        _favoriteScreenStateState.value.copy(
                            isLoading = true,
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun removeFavoriteItem() {
        val value = _favoriteScreenStateState.value
        if (value.products != null && value.products.isNotEmpty()) {
            _favoriteScreenStateState.value = value.copy(
                products = value.products.toMutableList().also {
                    it.removeAt(value.currentItem)
                }
            )
            _favoriteScreenStateState.value = _favoriteScreenStateState.value
        }

    }
}