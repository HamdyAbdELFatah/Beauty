package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.presentation.ui.theme.productBackground


@Composable
fun BottomCard(
    product: Product,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        elevation = 8.dp
    ) {
        Column {
            Text(
                text = "1",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Text(
                text = "2",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Text(
                text = "3",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "4",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "it",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "it",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "7",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "8",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "9",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "10",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "11",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "12",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "it",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = "14",
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )


        }
    }
}