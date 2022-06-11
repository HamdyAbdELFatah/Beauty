package com.hamdy.pinky.presentation.cart

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.presentation.cart.component.BottomCheckoutContainer
import com.hamdy.pinky.presentation.cart.component.CartItem
import com.hamdy.pinky.presentation.cart.component.ListCartProducts

@Composable
fun CartScreen(
    viewModel: CartScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.cartScreenStateState.value
    Log.d("lastMan", "CartScreen: stateChanged")
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val (listProducts, progress, checkoutContainer) = createRefs()
        state.products?.let { products ->
            if (products.isNotEmpty()) {
                ListCartProducts(
                    modifier = Modifier.constrainAs(listProducts) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(checkoutContainer.top)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                    products = products,
                    onClick = { position ->
                        viewModel.onEvent(
                            CartScreenEvent.CartProductClicked(
                                position,
                                navController
                            )
                        )
                    },
                    onAddClick = { position ->
                        viewModel.onEvent(CartScreenEvent.AddItemCount(position))
                    },
                    onMinusClick = { position ->
                        viewModel.onEvent(CartScreenEvent.ReduceItemCount(position))
                    },
                    onSwipedToDelete = { position ->
                        viewModel.onEvent(CartScreenEvent.SwipedToDeleted(position))
                    }
                )

                BottomCheckoutContainer(modifier = Modifier.constrainAs(checkoutContainer) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 48.dp)
                }, state = state) {
                    viewModel.onEvent(CartScreenEvent.CheckoutClicked(navController))
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.constrainAs(progress) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            })
        }

    }


}