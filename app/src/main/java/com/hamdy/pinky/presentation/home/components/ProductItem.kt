package com.hamdy.pinky.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hamdy.pinky.R
import com.hamdy.pinky.domain.model.Product

typealias ResString = R.string

@Composable
fun ProductItem(product: Product) {

    Box(modifier = Modifier.padding(8.dp)) {
        Card {
            Column {
                ProductImage(product.imageLink)
                ProductName(product.name)
            }
        }
    }
}

@Composable
private fun ProductImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = stringResource(id = ResString.product)
    )
}

@Composable
private fun ProductName(name: String) {
    Text(text = name)

}