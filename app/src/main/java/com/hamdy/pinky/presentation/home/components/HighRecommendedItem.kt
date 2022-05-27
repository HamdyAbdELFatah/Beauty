package com.hamdy.pinky.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamdy.pinky.R
import com.hamdy.pinky.presentation.ui.theme.productBackground
import com.hamdy.pinky.presentation.ui.theme.tertiary

private val productWidth = 160.dp
private val productHeight = 200.dp
const val highRecommendedItemId = 1047


@Composable
fun HighRecommendedItem(onClick: () -> Unit) {
    Box(modifier = Modifier) {
        Card(
            backgroundColor = productBackground,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            elevation = 4.dp
        ) {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(8.dp)) {
                PopularProduct()
                ProductDetails()
                ShopNowButton(onClick)
            }
        }
        ProductImage(Modifier.align(Alignment.TopEnd))
    }
}

@Composable
private fun ProductImage(modifier: Modifier) {
    Image(
        modifier = modifier.size(height = productHeight, width = productWidth),
        painter = painterResource(id = R.drawable.recommended_product),
        contentDescription = stringResource(
            id = R.string.recommended_product
        )
    )
}

@Composable
private fun ShopNowButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(stringResource(id = R.string.shop_now))
    }
}

@Composable
private fun ProductDetails() {
    Text(text = stringResource(id = R.string.high_recommended_product))

}

@Composable
private fun PopularProduct() {
    Text(
        text = stringResource(id = R.string.popular_now),
        fontSize = 10.sp,
        color = tertiary
    )

}