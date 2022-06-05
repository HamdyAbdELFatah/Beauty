package com.hamdy.pinky.presentation.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.common.ResString

@Composable
fun SnackBarSignState(
    modifier: Modifier,
    message: String,
    snackBarVisibleState: Boolean,
    onHideClick: (hide: Boolean) -> Unit
) {
    if (snackBarVisibleState) {
        Snackbar(
            modifier = modifier.padding(8.dp),
            action = {
                Button(
                    onClick = { onHideClick(false) }
                ) {
                    Text(stringResource(id = ResString.hide))
                }
            },
        ) { Text(text = message) }
    }

}
