package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.data.remote.model.ProductColor
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.presentation.ResDrawable
import com.hamdy.pinky.presentation.ui.theme.priceBackGround
import com.hamdy.pinky.presentation.ui.theme.primary
import com.hamdy.pinky.presentation.ui.theme.productBackground
import com.hamdy.pinky.presentation.ui.theme.subTitleColor

@Composable
fun BottomCard(
    product: Product,
    modifier: Modifier
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
            ListProductColors(product.productColors)
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
                CartActions(modifier = Modifier.align(Alignment.BottomCenter), {}, {}, {}, 0)
            }
        }
    }
}

@Composable
fun CartActions(
    modifier: Modifier,
    onAddToCartClick: () -> Unit,
    onAddClick: () -> Unit,
    onMinusClick: () -> Unit,
    count: Int

) {
    Row(modifier = modifier) {
        AddAndRemoveFromCart(
            onAddClick = onAddClick,
            onMinusClick = onMinusClick,
            count = count,
            modifier = Modifier.weight(1f)
        )
        AddToCartButton(onClick = onAddToCartClick, modifier = Modifier.weight(1.5f))

    }
}

@Composable
fun AddAndRemoveFromCart(
    modifier: Modifier,
    onAddClick: () -> Unit,
    onMinusClick: () -> Unit,
    count: Int
) {
    Row(
        modifier = modifier.background(
            color = productBackground,
            shape = RoundedCornerShape(8.dp)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundIconButton(
            icon = painterResource(ResDrawable.ic_minus),
            onClick = onAddClick,
            backgroundColor = primary,
            modifier = Modifier
        )
        Text(text = "$count", Modifier)
        RoundIconButton(
            icon = painterResource(ResDrawable.ic_add),
            onClick = onMinusClick,
            backgroundColor = primary,
            modifier = Modifier
        )
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
private fun ListProductColors(productColors: List<ProductColor>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(productColors.size) { item ->
            ProductColor(
                color = productColors[item].hexValue,
                onClick = {

                }
            )
        }
    }
}

@Composable
private fun ProductPrice(productPrice: String) {
    Box(
        modifier = Modifier
            .background(color = priceBackGround, shape = RoundedCornerShape(8.dp))
    ) {
        Text(
            text = "$$productPrice",
            Modifier
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
                .align(alignment = Alignment.TopCenter)
        )
    }
}

@Composable
private fun ProductColor(color: String, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Card(
            shape = CircleShape,
            modifier = Modifier.size(24.dp),
            backgroundColor = Color.parse(color)
        ) {

        }
    }
}

private fun Color.Companion.parse(colorString: String): Color =
    Color(color = android.graphics.Color.parseColor(colorString))