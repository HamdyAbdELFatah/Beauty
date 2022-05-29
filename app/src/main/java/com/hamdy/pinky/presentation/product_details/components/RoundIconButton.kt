package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.common.ResString

@Composable
fun RoundIconButton(icon: Painter, onClick: () -> Unit, backgroundColor: Color,modifier: Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Card(
            shape = CircleShape,
            backgroundColor = backgroundColor
        ) {
            Icon(
                painter = icon,
                contentDescription = stringResource(id = ResString.round_button),
                modifier = Modifier,
                tint = Color.White
            )
        }
    }
}