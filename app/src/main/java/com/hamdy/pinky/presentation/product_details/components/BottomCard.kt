package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.domain.model.Product

@Composable
fun BottomCard(
    product: Product,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        elevation = 8.dp
    ) {

    }
}