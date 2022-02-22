package com.casa98.currencies.presentation.coin_tweets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.casa98.currencies.presentation.components.SharedTopAppBar

@Composable
fun CoinTweetsScreen(
    onPopBackStack: () -> Unit,
    viewModel: CoinTweetsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            SharedTopAppBar(
                title = "${viewModel.appBarTitle.value} Tweets",
                onTapLeadingIcon = onPopBackStack
            )
        }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "${state.coinTweets.size} tweets for ${viewModel.appBarTitle.value}")
        }

        // If it's still loading:
        if(state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // If there are errors:
        if(state.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}