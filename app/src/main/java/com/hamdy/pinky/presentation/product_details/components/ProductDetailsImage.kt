package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.ImageLoader
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.common.initUntruestImageLoader
import com.hamdy.pinky.presentation.ui.theme.productBackground

@Composable
fun ProductDetailsImage(url: String, modifier: Modifier) {
    val untruestImageLoader: ImageLoader = initUntruestImageLoader(LocalContext.current)
//    AsyncImage(
//        modifier = modifier,
//        model = url,
//        contentDescription = stringResource(id = ResString.product),
//        contentScale = ContentScale.FillWidth,
//        error = painterResource(R.drawable.mekaup_place_holder),
//        imageLoader = untruestImageLoader
//    )
    val painter = rememberImagePainter(
        data = url,
        imageLoader = untruestImageLoader,
    )
    val imagePainter: Painter = if (painter.state is ImagePainter.State.Error) {
        painterResource(R.drawable.mekaup_place_holder)
    } else {
        painter
    }
    Image(
        modifier = modifier,
        painter = imagePainter,
        contentScale = ContentScale.FillWidth,
        contentDescription = stringResource(ResString.description)
    )
}

