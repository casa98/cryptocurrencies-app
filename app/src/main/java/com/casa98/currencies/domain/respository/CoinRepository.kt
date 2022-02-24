package com.casa98.currencies.domain.respository

import com.casa98.currencies.data.remote.dto.CoinDetailDto
import com.casa98.currencies.data.remote.dto.CoinDto
import com.casa98.currencies.data.remote.dto.TagInfoDto
import com.casa98.currencies.data.remote.dto.CoinTweetDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoin(coinId: String): CoinDetailDto

    suspend fun getCoinTweets(coinId: String): List<CoinTweetDto>

    suspend fun getTagInfo(tag: String): TagInfoDto
}