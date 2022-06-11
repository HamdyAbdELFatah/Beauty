package com.hamdy.pinky.presentation.cart.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.hamdy.pinky.domain.model.CartProduct

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListCartProducts(
    modifier: Modifier, products: List<CartProduct>,
    onAddClick: (id: Int) -> Unit,
    onMinusClick: (id: Int) -> Unit,
    onSwipedToDelete: (id: Int) -> Unit,
    onClick: (id: Int) -> Unit
) {

    LazyColumn(
        modifier = modifier
    ) {

        itemsIndexed(products, { _, products -> products.hashCode() })
        { position, item ->

            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                products.toMutableList().remove(item)
                onSwipedToDelete(position)
            }

            SwipeToDismiss(
                state = dismissState,
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.White
                            else -> Color.Red
                        }
                    )
                    val alignment = Alignment.CenterEnd
                    val icon = Icons.Default.Delete
                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = Dp(20f)),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Delete Icon",
                            modifier = Modifier.scale(scale)
                        )
                    }
                },
                dismissContent = {
                    CartItem(
                        product = products[position],
                        onClick = onClick,
                        onAddClick = { onAddClick(position) },
                        onMinusClick = { onMinusClick(position) }
                    )
                },
                directions = setOf(DismissDirection.EndToStart)
            )

        }
    }
}

