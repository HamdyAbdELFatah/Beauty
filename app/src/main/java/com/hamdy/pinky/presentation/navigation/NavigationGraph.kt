package com.hamdy.pinky.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hamdy.pinky.presentation.cart.CartScreen
import com.hamdy.pinky.presentation.favorite.FavoriteScreen
import com.hamdy.pinky.presentation.home.HomeScreen
import com.hamdy.pinky.presentation.navigation.BottomNavigationScreens
import com.hamdy.pinky.presentation.product_details.ProductDetailsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavigationScreens.route_home) {
        composable(BottomNavigationScreens.route_home) {
            HomeScreen(navController = navController)
        }
        composable(BottomNavigationScreens.route_favorite) {
            FavoriteScreen()
        }
        composable(BottomNavigationScreens.route_cart) {
            CartScreen()
        }
        composable(Screen.route_product_details) {
            ProductDetailsScreen()
        }


    }
}