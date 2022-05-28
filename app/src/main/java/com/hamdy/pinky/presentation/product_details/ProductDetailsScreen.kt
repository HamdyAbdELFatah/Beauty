package com.hamdy.pinky.presentation.product_details

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (productImage, backButton, BottomCard) = createRefs()
        val centerGuideLine1 = createGuidelineFromTop(0.3f)
        val centerGuideLine2 = createGuidelineFromTop(0.33f)
        state.product?.let { product ->
            ProductDetailsImage(
                product.imageLink,
                modifier = Modifier.constrainAs(productImage) {
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
                height = Dimension.preferredWrapContent
            })
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.constrainAs(BottomCard) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            })
        }
        if (state.error.isNotBlank()) {
            ErrorMessage(
                message = state.error,
                modifier = Modifier.constrainAs(BottomCard) {
                    centerHorizontallyTo(parent)
                    centerVerticallyTo(parent)
                })
        }
    }

}