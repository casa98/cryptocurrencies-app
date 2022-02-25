package com.casa98.currencies.presentation.main_screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.casa98.currencies.presentation.bottom_navigation.BottomNavigationBar
import com.casa98.currencies.presentation.bottom_navigation.NavigationGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavigationGraph(navController = navController)
    }
}

@Composable
fun FavoritesScreen() {
    Text("Favorites Screen")
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}