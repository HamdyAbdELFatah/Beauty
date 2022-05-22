package com.hamdy.pinky.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.use_case.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    private val _lipStickState = mutableStateOf(HomeState())
    val lipStickState: State<HomeState> = _lipStickState
    private val _eyelinerState = mutableStateOf(HomeState())
    val eyelinerState: State<HomeState> = _eyelinerState
    private val _blushState = mutableStateOf(HomeState())
    val blushState: State<HomeState> = _blushState

    init {
        getLipStickProducts("lipstick")
        getEyelinerProducts("eyeliner")
        getBlushProducts("blush")
    }

    private fun getLipStickProducts(category: String) {
        getProductsUseCase(category).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _lipStickState.value = HomeState(products = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _lipStickState.value = HomeState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _lipStickState.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getEyelinerProducts(category: String) {
        getProductsUseCase(category).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _eyelinerState.value = HomeState(products = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _eyelinerState.value = HomeState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _eyelinerState.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getBlushProducts(category: String) {
        getProductsUseCase(category).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _blushState.value = HomeState(products = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _blushState.value = HomeState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _blushState.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}