package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.common.Constants.parse

@Composable
fun ProductColor(position: Int, color: String, onClick: (position: Int) -> Unit) {
    Card(
        shape = CircleShape,
        modifier = Modifier
            .size(32.dp)
            .padding(4.dp)
            .noRippleClickable(onClick, position),
        backgroundColor = Color.parse(color),

        ) {

    }
}

@Composable
fun SelectedProductColor(position: Int, color: String, onClick: (position: Int) -> Unit) {
    Card(
        shape = CircleShape,
        modifier = Modifier
            .size(40.dp)
            .padding(4.dp)
            .noRippleClickable(onClick, position),
        backgroundColor = Color.parse(color),

        ) {

    }
}

inline fun Modifier.noRippleClickable(
    crossinline onClick: (id: Int) -> Unit,
    position: Int
): Modifier =
    composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick(position)
        }
    }