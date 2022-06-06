package com.hamdy.pinky.presentation.login.component

import android.opengl.Visibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.hamdy.pinky.common.Constants.SIGN_EMAIL_LABEL
import com.hamdy.pinky.common.Constants.SIGN_PASSWORD_LABEL
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.presentation.ui.theme.primary

@Composable
fun SignTextField(
    modifier: Modifier = Modifier,
    label: String,
    textValue: String,
    visibility: Boolean = true,
    isError: Boolean,
    fieldIcon: ImageVector,
    keyboardType: KeyboardType,
    onValueChange: (value: String) -> Unit = {},
    onPasswordVisibleClicked: (visibility: Boolean) -> Unit = {}

) {
    TextField(
        modifier = modifier,
        value = textValue,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(label) },
        visualTransformation =
        if (visibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),

        trailingIcon = {
            if (isError)
                Icon(Icons.Filled.Error, "error", tint = MaterialTheme.colorScheme.error)
            else if (label == SIGN_PASSWORD_LABEL) {
                PasswordIconVisibility(passwordVisible = visibility, onPasswordVisibleClicked)
            }
        },
        isError = isError,
        leadingIcon = { FieldIcon(fieldIcon) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = primary,
            focusedLabelColor = primary,
            cursorColor = primary,
            focusedIndicatorColor = primary,
            focusedLeadingIconColor = primary,
            focusedTrailingIconColor = primary,
            containerColor = Color.Transparent
        ),
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
    onPasswordVisibleClicked: (visibility: Boolean) -> Unit
) {
    val image = if (passwordVisible)
        Icons.Filled.Visibility
    else Icons.Filled.VisibilityOff


    IconButton(onClick = { onPasswordVisibleClicked(!passwordVisible) }) {
        Icon(imageVector = image, contentDescription = stringResource(id = ResString.login_icon))
    }
}

