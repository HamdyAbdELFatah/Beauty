package com.hamdy.pinky.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamdy.pinky.common.Resource
import com.hamdy.pinky.data.UserPreference
import com.hamdy.pinky.domain.use_case.LoginUseCase
import com.hamdy.pinky.domain.use_case.RegisterUseCase
import com.hamdy.pinky.domain.use_case.sign_form_validation_use_case.ValidateEmail
import com.hamdy.pinky.domain.use_case.sign_form_validation_use_case.ValidatePassword
import com.hamdy.pinky.domain.use_case.sign_form_validation_use_case.ValidateUserName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validateUserName: ValidateUserName,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val userPreference: UserPreference,
) : ViewModel() {

    var registerFormState by mutableStateOf(RegisterState())

    private fun register(useName: String, email: String, password: String) {
        registerUseCase(useName,email, password).onEach { result ->
            registerFormState = when (result) {
                is Resource.Success -> {
                    saveUser(result.data?.uid ?: "")
                    RegisterState(
                        isSuccess = result.data != null,
                    )
                }
                is Resource.Error -> {
                    registerFormState.copy(
                        error = result.message ?: "An unexpected error occurred",
                        isLoading = false,
                        emailError = null,
                        passwordError = null,
                        snackBarIsVisible = true
                    )
                }
                is Resource.Loading -> {
                    registerFormState.copy(
                        isLoading = true,
                        error = "",
                        emailError = null,
                        passwordError = null
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: RegisterFormEvent) {
        when (event) {
            is RegisterFormEvent.UserNameChanged -> {
                registerFormState = registerFormState.copy(userName = event.userName)
            }
            is RegisterFormEvent.EmailChanged -> {
                registerFormState = registerFormState.copy(email = event.email)
            }
            is RegisterFormEvent.PasswordChanged -> {
                registerFormState = registerFormState.copy(password = event.password)
            }
            is RegisterFormEvent.PasswordVisibilityChange -> {
                registerFormState = registerFormState.copy(passwordIsVisible = event.value)
            }
            is RegisterFormEvent.SnackBarVisibilityChange -> {
                registerFormState = registerFormState.copy(snackBarIsVisible = event.value)
            }
            is RegisterFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val userNameResult = validateUserName(registerFormState.userName)
        val emailResult = validateEmail(registerFormState.email)
        val passwordResult = validatePassword(registerFormState.password)

        val hasError = listOf(
            userNameResult,
            emailResult,
            passwordResult,
        ).any { !it.successful }

        if (hasError) {
            registerFormState = registerFormState.copy(
                userNameError = userNameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return
        }
        register(registerFormState.userName, registerFormState.email, registerFormState.password)
    }

    private fun saveUser(userId: String) {
        viewModelScope.launch {
            userPreference.saveUser(userId)
        }
    }

}