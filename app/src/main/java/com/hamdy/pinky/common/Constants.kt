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

    const val AN_UNEXPECTED_ERROR_OCCURRED: String = "anUnexpectedErrorOccurred"
    const val NO_INTERNET_CONNECTION: String = "noInternetConnection"



    fun Color.Companion.parse(colorString: String): Color =
        Color(color = android.graphics.Color.parseColor(colorString))
    // Firebase Constants
    const val USERS_COLLECTIONS: String = "Users"
    const val FAVORITES_COLLECTIONS: String = "Favorites"
    const val CART_COLLECTIONS: String = "Cart"
    const val USER_NAME_FIELD: String = "userName"
    const val USER_ID_FIELD: String = "userId"
    const val PRODUCT_ID_FIELD: String = "productId"
    const val TIME_STAMP_FIELD: String = "timeStamp"
}
