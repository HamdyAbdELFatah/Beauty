package com.hamdy.pinky.presentation.product_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.hamdy.pinky.presentation.product_details.components.BottomCard
import com.hamdy.pinky.presentation.product_details.components.ProductDetailsImage

@Composable
fun ProductDetailsScreen() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (productImage, backButton, BottomCard) = createRefs()
        val centerGuideLine1 = createGuidelineFromTop(0.4f)
        val centerGuideLine2 = createGuidelineFromTop(0.45f)
        ProductDetailsImage("", modifier = Modifier.constrainAs(productImage) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(centerGuideLine2)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })
        BottomCard(modifier = Modifier.constrainAs(BottomCard) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.top)
            top.linkTo(centerGuideLine1)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })

    }
}