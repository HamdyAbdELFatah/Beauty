package com.hamdy.pinky.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource


@Composable
fun TextHaveAccount(
    modifier: Modifier, onSignClick: () -> Unit, haveAccountTextId: Int,
    signTextId: Int
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
        ) {
        Text(
            text = stringResource(id = haveAccountTextId)
        )
        TextButton(onClick = onSignClick) {
            Text(
                text = stringResource(
                    id = signTextId
                )
            )
        }
    }
}
