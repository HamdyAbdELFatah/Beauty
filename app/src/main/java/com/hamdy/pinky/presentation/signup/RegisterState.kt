package com.hamdy.pinky.presentation.login

import com.hamdy.pinky.domain.model.Product

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = "",

    val email: String = "",
    val emailError: Int? = null,
    val password: String = "",
    val passwordError: Int? = null,
    val passwordIsVisible: Boolean = false,
    val snackBarIsVisible: Boolean = true,
)