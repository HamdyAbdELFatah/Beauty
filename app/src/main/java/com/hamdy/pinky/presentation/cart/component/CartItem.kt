package com.hamdy.pinky.presentation.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.ImageLoader
import coil.compose.AsyncImage
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.common.initUntruestImageLoader
import com.hamdy.pinky.domain.model.CartProduct
import com.hamdy.pinky.presentation.product_details.components.AddAndRemoveFromCart
import com.hamdy.pinky.presentation.ui.theme.primary
import com.hamdy.pinky.presentation.ui.theme.productBackground


@Composable
fun CartItem(
    modifier: Modifier = Modifier, product: CartProduct,
    onAddClick: () -> Unit,
    onMinusClick: () -> Unit,
    onClick: (id: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .noRippleClickable(onClick, product.id),
        backgroundColor = productBackground,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (image, detailsContainer) = createRefs()
            val centerGuideLine = createGuidelineFromStart(0.35f)

            Card(
                modifier = Modifier.constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(centerGuideLine)
                    width = Dimension.fillToConstraints
                    height = Dimension.ratio("1:1.2")
                },
                backgroundColor = productBackground,
                shape = RoundedCornerShape(8.dp)
            ) {
                ProductImage(product.imageLink)
            }
            Column(
                modifier = Modifier.constrainAs(detailsContainer) {
                    start.linkTo(centerGuideLine, margin = 4.dp)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }, verticalArrangement = Arrangement.SpaceBetween
            ) {
                DetailsText(product.name)
                Row {
                    Text("  ${product.priceSign}", color = primary)
                    Text(product.price)
                }
                DetailsText(product.productColor)
                AddAndRemoveFromCart(
                    modifier = Modifier.background(color = productBackground),
                    onAddClick = onAddClick,
                    onMinusClick = onMinusClick,
                    count = product.productCount
                )
            }
        }

    }
}

@Composable
private fun DetailsText(text: String) {
    Text(text = "  $text", maxLines = 1)
}

inline fun Modifier.noRippleClickable(
    crossinline onClick: (id: Int) -> Unit,
    productId: Int
): Modifier =
    composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick(productId)
        }
    }

@Composable
private fun ProductImage(url: String) {
    val untruestImageLoader: ImageLoader = initUntruestImageLoader(LocalContext.current)
    AsyncImage(
        modifier = Modifier.fillMaxWidth(),
        model = url,
        contentDescription = stringResource(id = ResString.product),
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.mekaup_place_holder),
        imageLoader = untruestImageLoader
    )

}

