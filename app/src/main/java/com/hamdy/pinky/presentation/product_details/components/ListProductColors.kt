package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.data.remote.model.ProductColor


@Composable
fun ListProductColors(
    selectedColor: Int,
    productColors: List<ProductColor>,
    onClick: (position: Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(productColors.size) { position ->
            if (selectedColor == position) {
                SelectedProductColor(
                    position = position,
                    color = productColors[position].hexValue,
                    onClick = onClick
                )
            } else {
                ProductColor(
                    position = position,
                    color = productColors[position].hexValue,
                    onClick = onClick
                )
            }
        }
    }
}