package com.hamdy.pinky.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hamdy.pinky.R
import com.hamdy.pinky.common.ResString
import com.hamdy.pinky.presentation.Screen
import com.hamdy.pinky.presentation.home.components.*


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val lipStickState = viewModel.lipStickState.value
    val eyelinerState = viewModel.eyelinerState.value
    val blushState = viewModel.blushState.value

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 64.dp)
        ) {
            HighRecommendedItem(onClick =  {
                navController.navigate(Screen.ProductDetails.route + "/${highRecommendedItemId}")

            })
            CategoryName(ResString.lipstick)
            if (lipStickState.error.isNotBlank()) {
                ErrorMessage(lipStickState.error)
            }
            if (lipStickState.isLoading) {
                LoadingProgress()
            }
            if (lipStickState.products.isNotEmpty()) {
                ProductsList(lipStickState.products)
            }
            CategoryName(ResString.eyeliner)
            if (eyelinerState.error.isNotBlank()) {
                ErrorMessage(eyelinerState.error)

            }
            if (eyelinerState.isLoading) {
                LoadingProgress()
            }
            if (eyelinerState.products.isNotEmpty()) {
                ProductsList(eyelinerState.products)
            }
            CategoryName(ResString.blush)
            if (blushState.error.isNotBlank()) {
                ErrorMessage(blushState.error)
            }
            if (blushState.isLoading) {
                LoadingProgress()
            }
            if (blushState.products.isNotEmpty()) {
                ProductsList(blushState.products)
            }
        }


    }
}


