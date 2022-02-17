package com.casa98.currencies.domain.respository

import com.casa98.currencies.data.remote.dto.CoinDetailDto
import com.casa98.currencies.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoin(coinId: String): CoinDetailDto
}