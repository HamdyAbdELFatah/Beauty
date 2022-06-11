package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.presentation.ResDrawable
import com.hamdy.pinky.presentation.ui.theme.primary
import com.hamdy.pinky.presentation.ui.theme.productBackground


@Composable
fun AddAndRemoveFromCart(
    modifier: Modifier,
    onAddClick: () -> Unit,
    onMinusClick: () -> Unit,
    count: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RoundIconButton(
            icon = painterResource(ResDrawable.ic_minus),
            onClick =  onMinusClick ,
            backgroundColor = primary,
            modifier = Modifier
        )
        Text(text = "$count", Modifier, color = Color.Black)
        RoundIconButton(
            icon = painterResource(ResDrawable.ic_add),
            onClick = onAddClick,
            backgroundColor = primary,
            modifier = Modifier
        )
    }
}