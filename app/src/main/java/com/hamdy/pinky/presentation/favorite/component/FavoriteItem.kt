package com.hamdy.pinky.presentation.favorite.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.BlurTransformation
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.common.initUntruestImageLoader
import com.hamdy.pinky.domain.model.FavoriteProduct
import com.hamdy.pinky.presentation.ui.theme.productBackground

@Composable
fun FavoriteItem(
    modifier: Modifier = Modifier,
    width: Double,
    height: Double,
    product: FavoriteProduct,
    onClick: (id: Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp * width).dp
    val screenHeight = (configuration.screenWidthDp * height).dp
    Card(
        modifier = modifier
            .width(screenWidth)
            .height(screenHeight)
            .padding(8.dp)
            .noRippleClickable(onClick, product.id),
        backgroundColor = productBackground,
        shape = RoundedCornerShape(8.dp)
    ) {
        ProductImage(product.imageLink)
    }

}

inline fun Modifier.noRippleClickable(
    crossinline onClick: (id: Int) -> Unit,
    productId: Int
): Modifier =
    composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick(productId)
        }
    }

@Composable
private fun ProductImage(url: String) {
    val untruestImageLoader: ImageLoader = initUntruestImageLoader(LocalContext.current)
    val painter = rememberImagePainter(
        data = url,
        imageLoader = untruestImageLoader,
        builder = {
            crossfade(500)
        },
    )
    val imagePainter: Painter = if (painter.state is ImagePainter.State.Error) {
        painterResource(R.drawable.mekaup_place_holder)
    } else {
        painter
    }
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = imagePainter,
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(ResString.description)
    )
//    AsyncImage(
//        modifier = Modifier.fillMaxWidth(),
//        model = ImageRequest.Builder(LocalContext.current)
//            .data(url)
//            .crossfade(true)
//            .build(),
//        contentDescription = stringResource(id = ResString.product),
//        contentScale = ContentScale.FillBounds,
//        error = painterResource(R.drawable.mekaup_place_holder),
//        imageLoader = untruestImageLoader
//    )

}

