package com.hamdy.pinky.presentation.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeastWrapContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hamdy.pinky.presentation.Screen
import com.hamdy.pinky.presentation.login.component.LogoContainer
import com.hamdy.pinky.presentation.login.component.SnackBarSignState
import com.hamdy.pinky.presentation.signup.component.BottomCard

@Composable
fun SignUpScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.registerFormState
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (logoImage, bottomCard, progress, snackBar) = createRefs()
        val centerGuideLine = createGuidelineFromTop(0.15f)
        LogoContainer(
            modifier = Modifier
                .constrainAs(logoImage) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(bottomCard.top)
                    bottom.linkTo(bottomCard.top)
                    height = Dimension.ratio("1:1")
                    width = Dimension.percent(0.30f)
                }
                .zIndex(1f)
        )

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
            onUserNameValueChange = {
                viewModel.onEvent(RegisterFormEvent.UserNameChanged(it))
            },
            onEmailValueChange = {
                viewModel.onEvent(RegisterFormEvent.EmailChanged(it))
            },
            onPasswordValueChange = {
                viewModel.onEvent(RegisterFormEvent.PasswordChanged(it))
            },
            onPasswordVisibleClicked = {
                viewModel.onEvent(RegisterFormEvent.PasswordVisibilityChange(it))
            },
            onRegisterButtonClicked = {
                viewModel.onEvent(RegisterFormEvent.Submit)
            },
            onSignClick = {
                navController.popBackStack()
                navController.navigate(Screen.route_login)
            }
        )

        if (state.isSuccess) {
            LaunchedEffect(state.isSuccess) {
                navController.popBackStack()
            }
        }
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
                    viewModel.onEvent(RegisterFormEvent.SnackBarVisibilityChange(it))
                },
                snackBarVisibleState = state.snackBarIsVisible
            )
        }
    }

}