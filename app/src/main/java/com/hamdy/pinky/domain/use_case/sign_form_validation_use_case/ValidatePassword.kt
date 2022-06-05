package com.hamdy.pinky.domain.use_case.sign_form_validation_use_case

import com.hamdy.pinky.common.ResString
import javax.inject.Inject

class ValidatePassword @Inject constructor(){

    operator fun invoke(password: String): ValidationResult {
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = ResString.password_less_than_length_error
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}