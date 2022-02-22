package com.casa98.currencies.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun SharedTopAppBar(
    title: String,
    onTapLeadingIcon: () -> Unit
) {
    androidx.compose.material.TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onTapLeadingIcon) {
                Icon(Icons.Filled.ArrowBack, "Back button")
            }
        }
    )
}