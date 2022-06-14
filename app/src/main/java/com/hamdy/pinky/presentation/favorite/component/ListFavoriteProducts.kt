package com.hamdy.pinky.presentation.favorite.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.hamdy.pinky.domain.model.FavoriteProduct
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

@Composable
fun ListFavoriteProducts(
    modifier: Modifier = Modifier,
    products: List<FavoriteProduct>,
    onItemZoomed: (url: String) -> Unit,
    onClick: (id: Int) -> Unit,
) {
    val lazyState = rememberLazyListState()

    val centerPosition by remember {
        derivedStateOf {
            val visibleInfo = lazyState.layoutInfo.visibleItemsInfo
            if (visibleInfo.isEmpty()) -1
            else {
                val offset = (visibleInfo.last().index - visibleInfo.first().index) / 2
                visibleInfo.first().index + offset
            }
        }
    }

    LazyRow(
        modifier = modifier,
        state = lazyState,
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(products) { index, item ->
            if (index == centerPosition) {
                onItemZoomed(item.imageLink)
            }
            FavoriteItem(
                modifier = Modifier.animateContentSize(),
                product = item,
                width = if (index == centerPosition) 0.55 else 0.5,
                height = if (index == centerPosition) 0.75 else 0.70,
                onClick = { onClick(index) }
            )
        }
    }
}
