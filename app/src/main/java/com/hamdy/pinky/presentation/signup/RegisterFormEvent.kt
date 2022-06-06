package com.hamdy.pinky.presentation.login

sealed class LoginFormEvent {

    data class EmailChanged(val email: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()
    data class PasswordVisibilityChange(val value: Boolean) : LoginFormEvent()
    data class SnackBarVisibilityChange(val value: Boolean) : LoginFormEvent()
    object Submit : LoginFormEvent()
}