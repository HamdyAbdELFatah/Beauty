package com.hamdy.pinky.presentation.signup.component

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.common.Constants.SIGN_EMAIL_LABEL
import com.hamdy.pinky.common.Constants.SIGN_PASSWORD_LABEL
import com.hamdy.pinky.common.Constants.SIGN_USERNAME_LABEL
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.presentation.login.component.SignTextField
import com.hamdy.pinky.presentation.login.component.TextFieldErrorMessage
import com.hamdy.pinky.presentation.login.component.TextHaveAccount
import com.hamdy.pinky.presentation.signup.RegisterState
import com.hamdy.pinky.presentation.ui.theme.primary

@Composable
fun BottomCard(
    modifier: Modifier,
    state: RegisterState,
    onRegisterButtonClicked: () -> Unit,
    onUserNameValueChange: (value: String) -> Unit,
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
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                SignTextField(
                    label = SIGN_USERNAME_LABEL,
                    textValue = state.userName,
                    fieldIcon = Icons.Filled.Person,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Text,
                    onValueChange = onUserNameValueChange,
                    isError = state.userNameError != null
                )
                if (state.userNameError != null) {
                    TextFieldErrorMessage(state.userNameError)
                }
                Spacer(modifier = Modifier.height(24.dp))
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
                    TextFieldErrorMessage(state.emailError)
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
                    TextFieldErrorMessage(state.passwordError)
                }
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onRegisterButtonClicked,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(
                            id = ResString.sign_up
                        )
                    )
                }
                TextHaveAccount(
                    modifier = Modifier.fillMaxWidth(),
                    haveAccountTextId = ResString.already_have_an_account,
                    signTextId = ResString.sign_in,
                    onSignClick = onSignClick
                )
            }
        }
    }
}


