package com.hamdy.pinky.presentation.cart

import androidx.navigation.NavHostController


sealed class CartScreenEvent {

    data class CartProductClicked(val itemPosition: Int,val navController: NavHostController) : CartScreenEvent()
    data class AddItemCount(val itemPosition: Int) : CartScreenEvent()
    data class ReduceItemCount(val itemPosition: Int) : CartScreenEvent()
    data class SwipedToDeleted(val itemPosition: Int)  : CartScreenEvent()
    data class CheckoutClicked(val navController: NavHostController) : CartScreenEvent()


}
