package com.hamdy.pinky.presentation.product_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hamdy.pinky.presentation.product_details.components.BottomCard
import com.hamdy.pinky.presentation.product_details.components.ErrorMessage
import com.hamdy.pinky.presentation.product_details.components.ProductDetailsImage

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.value

    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (productImage, backButton, BottomCard) = createRefs()
        val centerGuideLine1 = createGuidelineFromTop(0.4f)
        val centerGuideLine2 = createGuidelineFromTop(0.43f)
        state.product?.let { product ->
            ProductDetailsImage(product.imageLink, modifier = Modifier.constrainAs(productImage) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(centerGuideLine2)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            })
            BottomCard(product = product, modifier = Modifier.constrainAs(BottomCard) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(centerGuideLine1)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            })
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.constrainAs(BottomCard) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            })
        }
        if (state.error.isNotBlank()) {
            ErrorMessage(message = state.error, modifier = Modifier.constrainAs(BottomCard) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            })
        }
    }
}