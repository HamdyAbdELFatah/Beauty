package com.hamdy.pinky.presentation.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CategoryName(id: Int) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = stringResource(id = id),
        fontWeight = FontWeight.Bold

    )
}