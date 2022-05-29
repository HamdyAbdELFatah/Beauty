package com.hamdy.pinky.presentation.product_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.presentation.ui.theme.selected
import com.hamdy.pinky.presentation.ui.theme.unSelected

@Composable
fun FavoriteButton(
    isSelected: Boolean,
    modifier: Modifier, onClick: () -> Unit
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Card(
            shape = CircleShape,
            elevation = 4.dp
        ) {
            Icon(
                imageVector = if (isSelected) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = stringResource(id = ResString.favorite_button),
                tint = if (isSelected) selected else unSelected,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewFavoriteButton() {
    Column(modifier = Modifier) {
        FavoriteButton(false, Modifier,{})
        FavoriteButton(true, Modifier,{})
    }

}
