package com.hamdy.pinky.domain.use_case.sign_form_validation_use_case

import androidx.annotation.StringRes

data class ValidationResult(
    val successful: Boolean,
    @StringRes  val errorMessage: Int? = null
)