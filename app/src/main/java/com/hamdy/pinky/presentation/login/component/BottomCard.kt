package com.hamdy.pinky.presentation.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.common.Constants.SIGN_EMAIL_LABEL

@Composable
fun BottomCard(
    modifier: Modifier,
    onLoginButtonClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            LoginTextField(label = SIGN_EMAIL_LABEL, fieldIcon = Icons.Filled.Email)
            LoginTextField(label = SIGN_EMAIL_LABEL, fieldIcon = Icons.Filled.Lock)
        }
    }
}
