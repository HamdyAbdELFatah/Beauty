package com.hamdy.pinky.presentation.login.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.presentation.ui.theme.primary

@Composable
fun LogoContainer(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(2.dp, color = primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.beauty_logo),
            contentDescription = stringResource(id = ResString.logo),
            )
    }
}