package com.hamdy.pinky.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

open class BottomNavigationScreens(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
)