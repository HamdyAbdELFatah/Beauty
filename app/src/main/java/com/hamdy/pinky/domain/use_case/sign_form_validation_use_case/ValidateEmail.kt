package com.hamdy.pinky.domain.use_case.sign_form_validation_use_case

import android.util.Patterns
import androidx.compose.ui.res.stringResource
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString
import javax.inject.Inject

class ValidateEmail @Inject constructor(){

    operator fun invoke(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage =  ResString.the_email_can_not_be_blank
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = ResString.that_is_not_a_valid_email
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}