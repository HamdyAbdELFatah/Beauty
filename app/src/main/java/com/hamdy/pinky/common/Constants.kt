package com.hamdy.pinky.common

import androidx.compose.ui.graphics.Color
import com.hamdy.pinky.R

typealias ResString = R.string

object Constants {
    const val BASE_URL = "http://makeup-api.herokuapp.com/api/v1/"
    const val PREFERENCES_DB: String = "PreferencesDb"

    const val PARAM_PRODUCT_ID: String = "productId"
    const val SIGN_EMAIL_LABEL: String = "Email"
    const val SIGN_PASSWORD_LABEL: String = "Password"
    const val SIGN_USERNAME_LABEL: String = "UserName"



    fun Color.Companion.parse(colorString: String): Color =
        Color(color = android.graphics.Color.parseColor(colorString))
    // Firebase Constants
    const val USERS_COLLECTIONS: String = "Users"
    const val USER_NAME_FIELD: String = "userName"
    const val USER_ID_FIELD: String = "userId"
}
