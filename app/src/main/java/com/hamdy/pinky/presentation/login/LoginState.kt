package com.hamdy.pinky.presentation.login

import com.hamdy.pinky.domain.model.Product

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)