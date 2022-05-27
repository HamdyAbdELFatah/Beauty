package com.hamdy.pinky.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hamdy.pinky.common.Constants.PARAM_PRODUCT_ID
import com.hamdy.pinky.presentation.Screen
import com.hamdy.pinky.presentation.cart.CartScreen
import com.hamdy.pinky.presentation.favorite.FavoriteScreen
import com.hamdy.pinky.presentation.home.HomeScreen
import com.hamdy.pinky.presentation.product_details.ProductDetailsScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    changeVisibility: (isVisible: Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.route_home) {
        composable(route = Screen.route_home) {
            HomeScreen(navController = navController)
            changeVisibility(true)
        }
        composable(route = Screen.route_favorite) {
            FavoriteScreen()
            changeVisibility(true)
        }
        composable(route = Screen.route_cart) {
            changeVisibility(true)
            CartScreen()
        }
        composable(route = Screen.route_product_details + "/{$PARAM_PRODUCT_ID}") {
            changeVisibility(false)
            ProductDetailsScreen(navController = navController)
        }


    }
}