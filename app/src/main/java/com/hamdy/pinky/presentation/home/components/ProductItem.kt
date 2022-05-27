package com.hamdy.pinky.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.common.initUntruestImageLoader
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.presentation.ui.theme.primary
import com.hamdy.pinky.presentation.ui.theme.productBackground


private val productWidth = 140.dp
private val productHeight = 140.dp

@Composable
fun ProductItem(product: Product,onClick: (id:Int) -> Unit) {

    Box(
        modifier = Modifier
            .width(productWidth)
            .padding(8.dp)
            .noRippleClickable(onClick,product.id)
    ) {
        Column {
            Card(backgroundColor = productBackground, shape = RoundedCornerShape(8.dp)) {
                ProductImage(product.imageLink)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ProductDetails("$${product.price}")
            ProductDetails(product.name)
        }

    }
}
inline fun Modifier.noRippleClickable(crossinline onClick: (id:Int) -> Unit,productId :Int): Modifier =
    composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick(productId)
        }
    }

@Composable
private fun ProductImage(url: String) {
    val untruestImageLoader: ImageLoader = initUntruestImageLoader(LocalContext.current)
    AsyncImage(
        modifier = Modifier
            .height(productHeight)
            .fillMaxWidth(),
        model = url,
        contentDescription = stringResource(id = ResString.product),
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.mekaup_place_holder),
        imageLoader = untruestImageLoader
    )

}

@Composable
private fun ProductDetails(name: String) {
    Text(
        text = name,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = primary
    )

}


