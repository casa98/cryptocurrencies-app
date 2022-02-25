package com.casa98.currencies.presentation.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.casa98.currencies.R


sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Coin : NavigationItem("coins", Icons.Rounded.Home, "Coins")
    object Favorite : NavigationItem("favorite", Icons.Rounded.Star, "Favorite")
}