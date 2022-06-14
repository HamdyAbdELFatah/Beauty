package com.hamdy.pinky.presentation.favorite.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
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
import coil.request.ImageRequest
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.common.initUntruestImageLoader
import com.hamdy.pinky.presentation.ResDrawable

@Composable
fun BackgroundBlurProduct(url: String) {
    val untruestImageLoader: ImageLoader = initUntruestImageLoader(LocalContext.current)


    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(500)
            transformations(
                BlurTransformation(LocalContext.current, radius = 25f),
            )
        },
        imageLoader = untruestImageLoader
    )
    val imagePainter: Painter = if (painter.state is ImagePainter.State.Error) {
        painterResource(R.drawable.mekaup_place_holder)
    } else {
        painter
    }
    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = imagePainter,
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(ResString.description)
    )
//
//    AsyncImage(
//        modifier = Modifier
//            .fillMaxSize(),
//        model = ImageRequest.Builder(LocalContext.current)
//            .data(url)
//            .crossfade(3000)
//            .transformations(
//                BlurTransformation(LocalContext.current, radius = 50f)
//            )
//            .build(),
//        contentDescription = stringResource(id = ResString.product),
//        contentScale = ContentScale.Crop,
//        error = painterResource(ResDrawable.mekaup_place_holder),
//        imageLoader = untruestImageLoader
//    )
}


