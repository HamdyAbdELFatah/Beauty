package com.hamdy.pinky.domain.use_case.sign_form_validation_use_case

import com.hamdy.pinky.common.ResString
import javax.inject.Inject

class ValidateUserName  @Inject constructor(){

    operator fun invoke(userName: String): ValidationResult {
        if(userName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage =  ResString.user_name_is_empty_error_message
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}