package com.casa98.currencies.presentation.bottom_navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.casa98.currencies.presentation.Screen
import com.casa98.currencies.presentation.coins_screen.coin_detail.CoinDetailScreen
import com.casa98.currencies.presentation.coins_screen.coin_list.CoinListScreen
import com.casa98.currencies.presentation.coins_screen.coin_tweets.CoinTweetsScreen
import com.casa98.currencies.presentation.main_screen.FavoritesScreen

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        NavigationItem.Coin,
        NavigationItem.Favorite
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.5f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = NavigationItem.Coin.route,
    ) {
        composable(
            route = NavigationItem.Coin.route
        ) {
            CoinListScreen(navController)
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
        composable(NavigationItem.Favorite.route) {
            FavoritesScreen()
        }
    }
}