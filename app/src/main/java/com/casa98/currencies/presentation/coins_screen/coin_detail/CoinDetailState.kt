package com.casa98.currencies.presentation.coins_screen.coin_detail

import com.casa98.currencies.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = "",
)
