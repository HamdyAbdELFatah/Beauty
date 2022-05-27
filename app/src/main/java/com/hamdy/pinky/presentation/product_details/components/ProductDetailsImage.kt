package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.hamdy.pinky.presentation.ui.theme.productBackground

@Composable
fun ProductDetailsImage(url: String, modifier: Modifier) {
    val untruestImageLoader: ImageLoader = initUntruestImageLoader(LocalContext.current)
    Box(modifier = modifier.background(productBackground)) {
        AsyncImage(
            modifier = modifier.fillMaxSize(),
            model = url,
            contentDescription = stringResource(id = ResString.product),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.mekaup_place_holder),
            imageLoader = untruestImageLoader
        )
    }
}

