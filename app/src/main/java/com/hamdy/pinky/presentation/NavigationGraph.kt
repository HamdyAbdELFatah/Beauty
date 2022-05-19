package com.hamdy.pinky.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hamdy.pinky.presentation.cart.CartScreen
import com.hamdy.pinky.presentation.favorite.FavoriteScreen
import com.hamdy.pinky.presentation.home.HomeScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.route_home) {
        composable(Screen.route_home) {
            HomeScreen()
        }
        composable(Screen.route_favorite) {
            FavoriteScreen()
        }
        composable(Screen.route_cart) {
            CartScreen()
        }


    }
}