package com.hamdy.pinky.presentation.product_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeastWrapContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hamdy.pinky.presentation.Screen
import com.hamdy.pinky.presentation.home.components.highRecommendedItemId
import com.hamdy.pinky.presentation.product_details.components.*

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.value
    val selectedColor = viewModel.selectedColorState.value
    val itemInCartState = viewModel.itemInCartState.value
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (productImage, backButton, bottomCard, favoriteButton,progress) = createRefs()
        val centerGuideLine1 = createGuidelineFromTop(0.35f)
        val centerGuideLine2 = createGuidelineFromTop(0.37f)
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
            BottomCard(
                product = product,
                modifier = Modifier.constrainAs(bottomCard) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(centerGuideLine1)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints.atLeastWrapContent
                },
                itemCountInCart = itemInCartState,
                selectedColor = selectedColor, onColorSelected = { viewModel.selectColor(it) },
                onAddClick = { viewModel.changeItemCountInCart(it) },
                onMinusClick = { viewModel.changeItemCountInCart(it) },
                onAddToCartClick = {}
            )
            FavoriteButton(
                isSelected = false, modifier = Modifier.constrainAs(favoriteButton) {
                    end.linkTo(parent.end, margin = 24.dp)
                    bottom.linkTo(bottomCard.top)
                    top.linkTo(bottomCard.top)

                }, onClick = {
                    // todo add item to favorite list
                    navController.navigate(Screen.Login.route)
                }
            )
            BackButton(
                modifier = Modifier.constrainAs(backButton) {
                    start.linkTo(productImage.start, margin = 8.dp)
                    top.linkTo(productImage.top, margin = 8.dp)

                }, onClick = {
                    navController.popBackStack()
                }
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.constrainAs(progress) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            })
        }
        if (state.error.isNotBlank()) {
            ErrorMessage(
                message = state.error,
                modifier = Modifier.constrainAs(bottomCard) {
                    centerHorizontallyTo(parent)
                    centerVerticallyTo(parent)
                })
        }
    }

}