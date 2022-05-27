package com.hamdy.pinky.presentation.product_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamdy.pinky.common.Constants.PARAM_PRODUCT_ID
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.use_case.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(ProductDetailsState())
    val state: State<ProductDetailsState> = _state

    init {
            savedStateHandle.get<String>(PARAM_PRODUCT_ID)?.let { productId ->
                getProductDetails(productId.toInt())
            }
    }
    private fun getProductDetails(id: Int) {
        getProductDetailsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ProductDetailsState(product = result.data)
                }
                is Resource.Error -> {
                    _state.value = ProductDetailsState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ProductDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}