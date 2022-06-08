package com.hamdy.pinky.presentation.login.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TextFieldErrorMessage(errorMessageId:Int,textAlign:TextAlign) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = errorMessageId),
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.titleSmall,
    )
}