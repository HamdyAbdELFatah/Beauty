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
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getProductsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HomeState(products = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = HomeState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}