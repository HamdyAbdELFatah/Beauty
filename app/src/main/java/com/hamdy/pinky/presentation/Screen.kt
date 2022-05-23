package com.hamdy.pinky.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString

typealias ResDrawable = R.drawable

sealed class Screen(
    val route: String,
) {


    object ProductDetails : Screen(route_product_details)

    companion object {
        const val route_product_details = "ProductDetails"
    }

}
