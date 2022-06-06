package com.hamdy.pinky.presentation.signup

sealed class RegisterFormEvent {

    data class EmailChanged(val email: String) : RegisterFormEvent()
    data class PasswordChanged(val password: String) : RegisterFormEvent()
    data class UserNameChanged(val userName: String) : RegisterFormEvent()
    data class PasswordVisibilityChange(val value: Boolean) : RegisterFormEvent()
    data class SnackBarVisibilityChange(val value: Boolean) : RegisterFormEvent()
    object Submit : RegisterFormEvent()
}