package com.casa98.currencies.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.casa98.currencies.presentation.Screen
import com.casa98.currencies.presentation.coin_detail.CoinDetailScreen
import com.casa98.currencies.presentation.coin_list.CoinListScreen
import com.casa98.currencies.presentation.coin_tweets.CoinTweetsScreen
import com.casa98.currencies.presentation.ui.theme.CurrenciesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrenciesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val systemUiController = rememberSystemUiController()
                    // systemUiController.setStatusBarColor(Color.Transparent)

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route,
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route,
                        ) {
                            CoinListScreen(navController = navController)
                        }
                        composable(
                            route = "${Screen.CoinDetailScreen.route}/{coinId}",
                        ) {
                            CoinDetailScreen(navController = navController)
                        }
                        composable(
                            route = "${Screen.CoinTweetsScreen.route}/{coinId}",
                        ) {
                            CoinTweetsScreen(onPopBackStack = {
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CurrenciesTheme {
        Greeting("Android")
    }
}