package com.hamdy.pinky.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    private val _emailTextState = mutableStateOf("")
    val emailTextState: State<String> = _emailTextState

    private val _passwordTextState = mutableStateOf("")
    val passwordTextState: State<String> = _passwordTextState

    private val _passwordVisibilityState = mutableStateOf(false)
    val passwordVisibilityState: State<Boolean> = _passwordVisibilityState
    private val _snackBarVisibilityState = mutableStateOf(false)
    val snackBarVisibilityState: State<Boolean> = _snackBarVisibilityState

    fun emailTextChange(value: String) {
        _emailTextState.value = value
    }

    fun passwordTextChange(value: String) {
        _passwordTextState.value = value
    }

    fun passwordVisibilityChange(value: Boolean) {
        _passwordVisibilityState.value = value
    }
    fun snackBarVisibilityChange(value: Boolean) {
        _snackBarVisibilityState.value = value
    }

    fun login(email: String, password: String) {
        loginUseCase(email, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = LoginState(isSuccess = result.data ?: false)
                }
                is Resource.Error -> {
                    _state.value = LoginState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}