package com.hamdy.pinky.presentation.login

import com.hamdy.pinky.domain.model.Product

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = "",

    val email: String = "h@gmail.com",
    val emailError: Int? = null,
    val password: String = "123456",
    val passwordError: Int? = null,
    val passwordIsVisible: Boolean = false,
    val snackBarIsVisible: Boolean = true,
)