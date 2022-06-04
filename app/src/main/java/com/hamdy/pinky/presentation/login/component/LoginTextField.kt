package com.hamdy.pinky.presentation.login.component

import android.opengl.Visibility
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.hamdy.pinky.common.Constants.SIGN_EMAIL_LABEL
import com.hamdy.pinky.common.Constants.SIGN_PASSWORD_LABEL
import com.hamdy.pinky.common.ResString

@Composable
fun LoginTextField(
    label: String,
    visibility: Boolean = true,
    fieldIcon: ImageVector,
    onEmailValueChange: (value: String) -> Unit = {},
    onPasswordValueChange: (value: String) -> Unit = {},
    passwordVisibleClicked: (visibility: Boolean) -> Unit = {}

) {
    TextField(
        value = "",
        onValueChange = {
            if (label == SIGN_EMAIL_LABEL)
                onEmailValueChange(it)
            else
                onPasswordValueChange(it)
        },
        label = { Text(label) },
        visualTransformation =
        if (visibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

        trailingIcon = {
            if (label == SIGN_PASSWORD_LABEL) {
                PasswordIconVisibility(passwordVisible = visibility, passwordVisibleClicked)
            }
        },
        leadingIcon = {
            FieldIcon(fieldIcon)

        }
    )
}

@Composable
private fun FieldIcon(fieldIcon: ImageVector) {
    Icon(
        imageVector = fieldIcon,
        contentDescription = stringResource(id = ResString.login_icon)
    )
}

@Composable
fun PasswordIconVisibility(
    passwordVisible: Boolean,
    passwordVisibleClicked: (visibility: Boolean) -> Unit
) {
    val image = if (passwordVisible)
        Icons.Filled.Visibility
    else Icons.Filled.VisibilityOff


    IconButton(onClick = { passwordVisibleClicked(!passwordVisible) }) {
        Icon(imageVector = image, contentDescription = stringResource(id = ResString.login_icon))
    }
}

}