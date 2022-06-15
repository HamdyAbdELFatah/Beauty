package com.hamdy.pinky.presentation.favorite

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hamdy.pinky.common.Constants.PARAM_Favorite_PRODUCT_ID
import com.hamdy.pinky.presentation.favorite.component.BackgroundBlurProduct
import com.hamdy.pinky.presentation.favorite.component.ListFavoriteProducts

@Composable
fun FavoriteScreen(
    viewModel: FavoriteScreenViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    var removedProductId: Int = -1
    navController.GetOnceResult<Int>(PARAM_Favorite_PRODUCT_ID) { productId ->
        removedProductId = productId
    }
    val state = viewModel.favoriteScreenStateState.value
    LaunchedEffect(removedProductId) {
        if (removedProductId != -1) {
            viewModel.removeFavoriteItem()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {

        state.products?.let { products ->

            if (state.products.isNotEmpty()) {
                if (state.imageBackgroundUrl.isNotBlank()) {
                    BackgroundBlurProduct(url = state.imageBackgroundUrl)
                }
            }
            ListFavoriteProducts(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .align(Alignment.Center),
                products = products,
                onItemZoomed = { url ->
                    viewModel.onEvent(
                        FavoriteScreenEvent.ChangeBackgroundImage(url = url)
                    )
                }
            ) { position ->
                viewModel.onEvent(
                    FavoriteScreenEvent.CartProductClicked(
                        itemPosition = position,
                        navController = navController
                    )
                )
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}

@Composable
fun <T> NavController.GetOnceResult(keyResult: String, onResult: (T) -> Unit) {
    val valueScreenResult = currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(keyResult)?.observeAsState()

    valueScreenResult?.value?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(keyResult)
    }
}
