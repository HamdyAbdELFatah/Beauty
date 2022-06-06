package com.hamdy.pinky.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.data.UserPreference
import com.hamdy.pinky.domain.use_case.LoginUseCase
import com.hamdy.pinky.domain.use_case.sign_form_validation_use_case.ValidateEmail
import com.hamdy.pinky.domain.use_case.sign_form_validation_use_case.ValidatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val userPreference: UserPreference,
) : ViewModel() {

    var loginFormState by mutableStateOf(LoginState())

    private fun login(email: String, password: String) {
        loginUseCase(email, password).onEach { result ->
            loginFormState = when (result) {
                is Resource.Success -> {
                    saveUser(result.data?.uid ?: "")
                    loginFormState.copy(
                        isSuccess = result.data != null,
                        isLoading = false,
                        emailError = null,
                        passwordError = null,
                        snackBarIsVisible = true,
                        error = ""
                    )
                }
                is Resource.Error -> {
                    loginFormState.copy(
                        error = result.message ?: "An unexpected error occurred",
                        isLoading = false,
                        emailError = null,
                        passwordError = null,
                        snackBarIsVisible = true
                    )
                }
                is Resource.Loading -> {
                    loginFormState.copy(
                        isLoading = true,
                        error = "",
                        emailError = null,
                        passwordError = null
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                loginFormState = loginFormState.copy(email = event.email)
            }
            is LoginFormEvent.PasswordChanged -> {
                loginFormState = loginFormState.copy(password = event.password)
            }
            is LoginFormEvent.PasswordVisibilityChange -> {
                loginFormState = loginFormState.copy(passwordIsVisible = event.value)
            }
            is LoginFormEvent.SnackBarVisibilityChange -> {
                loginFormState = loginFormState.copy(snackBarIsVisible = event.value)
            }
            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail(loginFormState.email)
        val passwordResult = validatePassword(loginFormState.password)

        val hasError = listOf(
            emailResult,
            passwordResult,
        ).any { !it.successful }

        if (hasError) {
            loginFormState = loginFormState.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return
        }
        login(loginFormState.email, loginFormState.password)
    }

    private fun saveUser(userId: String) {
        viewModelScope.launch {
            userPreference.saveUser(userId)
        }
    }

}