package com.hamdy.pinky.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hamdy.pinky.R
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.presentation.ui.theme.productBackground
import com.hamdy.pinky.presentation.ui.theme.tertiary

typealias ResString = R.string

private val productWidth = 140.dp
private val productHeight = 140.dp

@Composable
fun ProductItem(product: Product) {

    Box(
        modifier = Modifier
            .width(productWidth)
            .padding(8.dp)
    ) {
        Column {
            Card(backgroundColor = productBackground, shape = RoundedCornerShape(8.dp)) {
                ProductImage(product.imageLink)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ProductName(product.name)
        }

    }
}

@Composable
private fun ProductImage(url: String) {

    AsyncImage(
        modifier = Modifier
            .height(productHeight)
            .fillMaxWidth(),
        model = url,
        contentDescription = stringResource(id = ResString.product),
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.mekaup_place_holder),


        )

}

@Composable
private fun ProductName(name: String) {
    Text(text = name, maxLines = 1, overflow = TextOverflow.Ellipsis)

}