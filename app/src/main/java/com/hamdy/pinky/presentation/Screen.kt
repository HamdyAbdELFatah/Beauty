package com.hamdy.pinky.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.hamdy.pinky.R

typealias ResDrawable = R.drawable
typealias ResString = R.string

sealed class Screen(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {

    object Home : Screen(route_home, ResString.home, ResDrawable.ic_home)
    object Favorite : Screen(route_favorite, ResString.favorite, ResDrawable.ic_favorite)
    object Cart : Screen(route_cart, ResString.cart, ResDrawable.ic_cart)

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
