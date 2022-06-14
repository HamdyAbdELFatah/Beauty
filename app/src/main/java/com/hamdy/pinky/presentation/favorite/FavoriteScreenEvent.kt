package com.hamdy.pinky.presentation.favorite

import androidx.navigation.NavHostController


sealed class FavoriteScreenEvent {
    data class CartProductClicked(val itemPosition: Int, val navController: NavHostController) :
        FavoriteScreenEvent()

    data class ChangeBackgroundImage(val url: String) : FavoriteScreenEvent()
}
