package com.casa98.currencies.data.repository

import com.casa98.currencies.data.remote.CoinPaprikaApi
import com.casa98.currencies.data.remote.dto.CoinDetailDto
import com.casa98.currencies.data.remote.dto.CoinDto
import com.casa98.currencies.domain.respository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val  api: CoinPaprikaApi
): CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoin(coinId: String): CoinDetailDto {
        return api.getCoin(coinId)
    }
}