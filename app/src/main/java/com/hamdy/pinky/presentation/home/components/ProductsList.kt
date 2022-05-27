package com.hamdy.pinky.presentation.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hamdy.pinky.domain.model.Product

@Composable
fun ProductsList(list: List<Product>, onClick: (id: Int) -> Unit) {
    LazyRow(modifier = Modifier.fillMaxSize()) {
        items(list) { product ->

            ProductItem(product, onClick)

        }
    }
}
