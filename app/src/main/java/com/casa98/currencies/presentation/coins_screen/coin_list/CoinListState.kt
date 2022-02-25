package com.casa98.currencies.presentation.coins_screen.coin_list

import com.casa98.currencies.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = "",
)
