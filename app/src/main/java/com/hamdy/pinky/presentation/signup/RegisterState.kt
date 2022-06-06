package com.hamdy.pinky.presentation.signup

data class RegisterState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = "",

    val email: String = "",
    val emailError: Int? = null,
    val password: String = "",
    val passwordError: Int? = null,
    val userName: String = "",
    val userNameError: Int? = null,
    val passwordIsVisible: Boolean = false,
    val snackBarIsVisible: Boolean = true,
)