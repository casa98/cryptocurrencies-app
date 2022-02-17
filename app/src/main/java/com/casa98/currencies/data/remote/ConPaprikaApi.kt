package com.casa98.currencies.data.remote

import com.casa98.currencies.data.remote.dto.CoinDetailDto
import com.casa98.currencies.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ConPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoin(@Path("coinId") coinDto: String): CoinDetailDto
}