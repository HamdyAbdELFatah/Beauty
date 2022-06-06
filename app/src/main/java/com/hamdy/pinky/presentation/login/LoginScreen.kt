package com.hamdy.pinky.presentation.login

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeastWrapContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hamdy.pinky.presentation.Screen
import com.hamdy.pinky.presentation.login.component.BottomCard
import com.hamdy.pinky.presentation.login.component.LogoContainer
import com.hamdy.pinky.presentation.login.component.SnackBarSignState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.loginFormState

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (logoImage, bottomCard, progress, snackBar) = createRefs()
        val centerGuideLine = createGuidelineFromTop(0.35f)
        LogoContainer(
            modifier = Modifier.constrainAs(logoImage) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, margin = 16.dp)
                bottom.linkTo(centerGuideLine, margin = 16.dp)
                height = Dimension.fillToConstraints
                width = Dimension.ratio("1:1")
            })

        BottomCard(
            modifier = Modifier.constrainAs(bottomCard) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(centerGuideLine)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints.atLeastWrapContent
            },
            state = state,
            onEmailValueChange = {
                viewModel.onEvent(LoginFormEvent.EmailChanged(it))
            },
            onPasswordValueChange = {
                viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
            },
            onPasswordVisibleClicked = {
                viewModel.onEvent(LoginFormEvent.PasswordVisibilityChange(it))
            },
            onLoginButtonClicked = {
                viewModel.onEvent(LoginFormEvent.Submit)
            },
            onSignClick = {
                navController.popBackStack()
                navController.navigate(Screen.route_sign_up)
            }
        )
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.constrainAs(progress) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            })
        }
        if (state.error.isNotBlank()) {
            SnackBarSignState(
                message = state.error,
                modifier = Modifier.constrainAs(snackBar) {
                    bottom.linkTo(parent.bottom)
                },
                onHideClick = {
                    viewModel.onEvent(LoginFormEvent.SnackBarVisibilityChange(it))
                },
                snackBarVisibleState = state.snackBarIsVisible
            )
        }
    }

    if (state.isSuccess) {
        LaunchedEffect(state.isSuccess) {
            navController.popBackStack()
        }
    }
}