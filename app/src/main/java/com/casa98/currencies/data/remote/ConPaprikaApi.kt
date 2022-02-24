package com.casa98.currencies.data.remote

import com.casa98.currencies.data.remote.dto.CoinDetailDto
import com.casa98.currencies.data.remote.dto.CoinDto
import com.casa98.currencies.data.remote.dto.TagInfoDto
import com.casa98.currencies.data.remote.dto.CoinTweetDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoin(@Path("coinId") coinId: String): CoinDetailDto

    @GET("/v1/coins/{coinId}/twitter")
    suspend fun getCoinTweets(@Path("coinId") coinId: String): List<CoinTweetDto>

    @GET("/v1/tags/{tagId}")
    suspend fun getTagInfo(@Path("tagId") tagId: String): TagInfoDto
}