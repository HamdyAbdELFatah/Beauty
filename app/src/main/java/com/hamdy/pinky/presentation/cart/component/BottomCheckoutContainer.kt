package com.hamdy.pinky.presentation.cart.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.presentation.cart.CartScreenState
import com.hamdy.pinky.presentation.ui.theme.primary
import com.hamdy.pinky.presentation.ui.theme.productBackground

@Composable
fun BottomCheckoutContainer(
    modifier: Modifier,
    state: CartScreenState,
    onCheckoutClicked: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        DetailsTotal(ResString.sub_total, state.subTotal)
        DetailsTotal(ResString.shipping, state.shipping)
        Divider(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
            color = primary,
            thickness = 1.dp
        )
        DetailsTotal(ResString.total, state.total)
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onCheckoutClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = primary,
            )
        ) {
            Text(text = stringResource(id = ResString.checkout), color = Color.White)
        }
    }
}

@Composable
fun DetailsTotal(titleId: Int, price: Double) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = stringResource(id = titleId))
        Row {
            Text("$", color = primary)
            Text(text = if (price > 0) price.toString() else price.toInt().toString())
        }
    }
}