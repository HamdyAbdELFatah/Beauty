package com.hamdy.pinky.presentation.login.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.common.Constants.SIGN_EMAIL_LABEL
import com.hamdy.pinky.common.Constants.SIGN_PASSWORD_LABEL
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.presentation.login.LoginState
import com.hamdy.pinky.presentation.ui.theme.primary

@Composable
fun BottomCard(
    modifier: Modifier,
    state: LoginState,
    onLoginButtonClicked: () -> Unit,
    onEmailValueChange: (value: String) -> Unit,
    onPasswordValueChange: (value: String) -> Unit,
    onPasswordVisibleClicked: (visibility: Boolean) -> Unit,
    onSignClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceAround

        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                SignTextField(
                    label = SIGN_EMAIL_LABEL,
                    textValue = state.email,
                    fieldIcon = Icons.Filled.Email,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Email,
                    onValueChange = onEmailValueChange,
                    isError = state.emailError != null
                )
                if (state.emailError != null) {
                    TextFieldErrorMessage(state.emailError, TextAlign.End)
                }
                Spacer(modifier = Modifier.height(24.dp))
                SignTextField(
                    label = SIGN_PASSWORD_LABEL,
                    textValue = state.password,
                    fieldIcon = Icons.Filled.Lock,
                    modifier = Modifier.fillMaxWidth(),
                    visibility = state.passwordIsVisible,
                    keyboardType = KeyboardType.Password,
                    onValueChange = onPasswordValueChange,
                    onPasswordVisibleClicked = onPasswordVisibleClicked,
                    isError = state.passwordError != null
                )
                if (state.passwordError != null) {
                    TextFieldErrorMessage(state.passwordError, TextAlign.End)
                }
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onLoginButtonClicked,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(
                            id = ResString.sign_in
                        )
                    )
                }
                TextHaveAccount(
                    modifier = Modifier.fillMaxWidth(),
                    haveAccountTextId = ResString.do_not_have_an_account,
                    signTextId = ResString.sign_up,
                    onSignClick = onSignClick
                )
            }
        }
    }
}


