package com.hamdy.pinky.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hamdy.pinky.presentation.home.components.BottomNavigationBar
import com.hamdy.pinky.presentation.navigation.NavigationGraph
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
    val bottomBarVisibility = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        backgroundColor = background,
        bottomBar = {
            AnimatedVisibility(visible = bottomBarVisibility.value,
                content = {
                    BottomNavigationBar(
                        navController = navController
                    )
                }
            )
        }

    ) {
        NavigationGraph(navController = navController, changeVisibility = { isVisible ->
            bottomBarVisibility.value = isVisible
        })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PinkyTheme {
        AppScaffold(rememberNavController())
    }
}