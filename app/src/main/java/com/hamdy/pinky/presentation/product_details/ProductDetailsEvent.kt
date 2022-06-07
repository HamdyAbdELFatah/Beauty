package com.hamdy.pinky.presentation.product_details

import androidx.navigation.NavHostController


sealed class ProductDetailsEvent {

    data class ColorSelected(val colorPosition: Int) : ProductDetailsEvent()
    data class FavoriteButtonClicked(val navController: NavHostController) : ProductDetailsEvent()
    data class AddToCartClicked(val navController: NavHostController)  : ProductDetailsEvent()
    object AddItemCount : ProductDetailsEvent()
    object ReduceItemCount : ProductDetailsEvent()

}
