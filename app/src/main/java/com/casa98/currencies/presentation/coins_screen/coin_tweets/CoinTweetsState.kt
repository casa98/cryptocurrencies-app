package com.casa98.currencies.presentation.coins_screen.coin_tweets

import com.casa98.currencies.data.remote.dto.CoinTweetDto

data class CoinTweetsState(
    val isLoading: Boolean = false,
    val coinTweets: List<CoinTweetDto> = emptyList(),
    val error: String = "",
)
