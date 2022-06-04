package com.hamdy.pinky.presentation.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeastWrapContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hamdy.pinky.presentation.login.component.BottomCard
import com.hamdy.pinky.presentation.login.component.LogoContainer
import com.hamdy.pinky.presentation.product_details.components.ErrorMessage

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (logoImage, bottomCard) = createRefs()
        val centerGuideLine = createGuidelineFromTop(0.35f)
        LogoContainer(
            modifier = Modifier.constrainAs(logoImage) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(centerGuideLine)
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
            onLoginButtonClicked = {},
            onSignUpClicked = {}
        )


    }

}