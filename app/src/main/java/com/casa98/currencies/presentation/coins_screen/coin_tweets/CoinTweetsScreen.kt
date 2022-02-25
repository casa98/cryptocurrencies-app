package com.casa98.currencies.presentation.coins_screen.coin_tweets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.casa98.currencies.presentation.coins_screen.coin_tweets.components.TwittItem
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(state.coinTweets) { twitt ->
                TwittItem(twitt = twitt)
                Divider()
            }
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