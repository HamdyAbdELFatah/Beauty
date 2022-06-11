package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.presentation.ResDrawable
import com.hamdy.pinky.presentation.login.component.TextFieldErrorMessage
import com.hamdy.pinky.presentation.product_details.ProductDetailsState
import com.hamdy.pinky.presentation.ui.theme.primary
import com.hamdy.pinky.presentation.ui.theme.productBackground
import com.hamdy.pinky.presentation.ui.theme.subTitleColor

@Composable
fun BottomCard(
    modifier: Modifier,
    product: Product,
    state: ProductDetailsState,
    onColorSelected: (position: Int) -> Unit,
    onAddToCartClick: () -> Unit,
    onAddClick: () -> Unit,
    onMinusClick: () -> Unit,
    itemCountInCart: Int
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            TitleAndPrice(product)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = ResString.product_colors))
            if (state.selectedColorPositionError != null) {
                TextFieldErrorMessage(state.selectedColorPositionError, TextAlign.Start)
            }
            ListProductColors(
                selectedColor = state.selectedColorPosition,
                productColors = product.productColors,
                onClick = onColorSelected
            )
            Text(text = stringResource(id = ResString.description))
            Spacer(modifier = Modifier.height(8.dp))
            ProductDescription(product.description)
            Spacer(
                modifier = Modifier
                    .height(4.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .background(subTitleColor)
            )
            Box(modifier = Modifier.fillMaxSize()) {
                CartActions(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    state,
                    onAddToCartClick,
                    onAddClick,
                    onMinusClick,
                    itemCountInCart
                )
            }
        }
    }
}

@Composable
fun CartActions(
    modifier: Modifier,
    state: ProductDetailsState,
    onAddToCartClick: () -> Unit,
    onAddClick: () -> Unit,
    onMinusClick: () -> Unit,
    count: Int

) {
    Column(modifier = modifier) {
        if (state.cartItemCountError != null) {
            TextFieldErrorMessage(state.cartItemCountError, TextAlign.Start)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AddAndRemoveFromCart(
                onAddClick = onAddClick,
                onMinusClick = onMinusClick,
                count = count,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(4.dp)
                    .background(
                        color = productBackground,
                        shape = RoundedCornerShape(8.dp)
                    )

            )
            Spacer(modifier = Modifier.weight(0.25f))
            AddToCartButton(onClick = onAddToCartClick, modifier = Modifier.weight(1.5f))

        }
    }
}


@Composable
fun AddToCartButton(onClick: () -> Unit, modifier: Modifier) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = primary)
    ) {
        Text(text = stringResource(id = ResString.add_to_cart))
    }
}

@Composable
private fun TitleAndPrice(product: Product) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = product.name)
        ProductPrice(product.price)
    }
}

@Composable
private fun ProductDescription(description: String) {
    Text(
        text = description, maxLines = 3, overflow = TextOverflow.Ellipsis,
        fontSize = 12.sp,
        color = subTitleColor,
    )
}


@Composable
private fun ProductPrice(productPrice: String) {
    Box(
        modifier = Modifier
            .background(color = productBackground, shape = RoundedCornerShape(8.dp))
    ) {
        Text(
            text = "$$productPrice",
            Modifier
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
                .align(alignment = Alignment.TopCenter)
        )
    }
}



