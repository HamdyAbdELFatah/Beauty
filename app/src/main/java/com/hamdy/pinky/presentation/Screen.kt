package com.hamdy.pinky.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.presentation.navigation.BottomNavigationScreens

typealias ResDrawable = R.drawable

sealed class Screen(
    val route: String,
) {

    object Home : BottomNavigationScreens(route_home, ResString.home, ResDrawable.ic_home)
    object Favorite :
        BottomNavigationScreens(route_favorite, ResString.favorite, ResDrawable.ic_favorite)
    object Cart : BottomNavigationScreens(route_cart, ResString.cart, ResDrawable.ic_cart)

    object ProductDetails : Screen(route_product_details)
    object Login : Screen(route_login)
    object SignUp : Screen(route_sign_up)

    companion object {
        val screens = listOf(
            Home,
            Favorite,
            Cart,
        )
        const val route_home = "Home"
        const val route_favorite = "Favorite"
        const val route_cart = "Cart"
        const val route_product_details = "ProductDetails"
        const val route_login = "Login"
        const val route_sign_up = "SignUp"
    }

}
