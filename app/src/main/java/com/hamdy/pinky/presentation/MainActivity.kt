package com.hamdy.pinky.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hamdy.pinky.presentation.home.components.BottomNavigationBar
import com.hamdy.pinky.presentation.ui.theme.PinkyTheme
import com.hamdy.pinky.presentation.ui.theme.background
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PinkyTheme {
                val navController = rememberNavController()
                AppScaffold(navController)
            }
        }
    }
}

@Composable
fun AppScaffold(navController: NavHostController) {
    Scaffold(
        backgroundColor = background,
        topBar = {

        }, bottomBar = {
            BottomNavigationBar(navController = navController)
        }

    ) {

        NavigationGraph(navController = navController)

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PinkyTheme {
        AppScaffold(rememberNavController())
    }
}