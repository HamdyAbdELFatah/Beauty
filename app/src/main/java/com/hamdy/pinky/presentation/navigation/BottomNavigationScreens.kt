package com.hamdy.pinky.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.presentation.ResDrawable
import com.hamdy.pinky.presentation.Screen

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {

    object Home : BottomNavigationScreens(route_home, ResString.home, ResDrawable.ic_home)
    object Favorite :
        BottomNavigationScreens(route_favorite, ResString.favorite, ResDrawable.ic_favorite)

    object Cart : BottomNavigationScreens(route_cart, ResString.cart, ResDrawable.ic_cart)

    companion object {
        val screens = listOf(
            Home,
            Favorite,
            Cart,
        )
        const val route_home = "Home"
        const val route_favorite = "Favorite"
        const val route_cart = "Cart"
    }

}