package com.hamdy.pinky.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hamdy.pinky.common.Constants.PARAM_CART_PRODUCT_ID
import com.hamdy.pinky.common.Constants.PARAM_PRODUCT_ID
import com.hamdy.pinky.presentation.Screen
import com.hamdy.pinky.presentation.SplashScreen
import com.hamdy.pinky.presentation.cart.CartScreen
import com.hamdy.pinky.presentation.favorite.FavoriteScreen
import com.hamdy.pinky.presentation.home.HomeScreen
import com.hamdy.pinky.presentation.login.LoginScreen
import com.hamdy.pinky.presentation.product_details.ProductDetailsScreen
import com.hamdy.pinky.presentation.signup.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    changeVisibility: (isVisible: Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.route_splash) {
        composable(route = Screen.route_splash) {
            SplashScreen(navController = navController)
            changeVisibility(false)
        }
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
            CartScreen(navController = navController)
        }
        composable(route = Screen.route_product_details +
                "/{$PARAM_PRODUCT_ID}?$PARAM_CART_PRODUCT_ID={$PARAM_CART_PRODUCT_ID}",
            arguments = listOf(
                navArgument(PARAM_PRODUCT_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                    nullable = false
                },
                navArgument(PARAM_CART_PRODUCT_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            changeVisibility(false)
            ProductDetailsScreen(navController = navController)
        }
        composable(route = Screen.route_login) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.route_sign_up) {
            SignUpScreen(navController = navController)
        }

    }
}