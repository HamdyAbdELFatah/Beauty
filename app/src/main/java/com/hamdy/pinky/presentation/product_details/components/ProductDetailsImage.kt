package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.ImageLoader
import coil.compose.AsyncImage
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.common.initUntruestImageLoader

@Composable
fun ProductDetailsImage(url: String, modifier: Modifier) {
    Image(
        modifier =modifier,
        painter = painterResource(id = R.drawable.recommended_product),
        contentDescription = stringResource(
            id = R.string.recommended_product
        )
    )
//    val untruestImageLoader: ImageLoader = initUntruestImageLoader(LocalContext.current)
//    AsyncImage(
//        modifier = Modifier
//            .fillMaxWidth(),
//        model = url,
//        contentDescription = stringResource(id = ResString.product),
//        contentScale = ContentScale.Crop,
//        error = painterResource(R.drawable.mekaup_place_holder),
//        imageLoader = untruestImageLoader
//    )
}

