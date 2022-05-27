package com.hamdy.pinky.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.presentation.ResDrawable
import com.hamdy.pinky.presentation.Screen

open class BottomNavigationScreens(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
)