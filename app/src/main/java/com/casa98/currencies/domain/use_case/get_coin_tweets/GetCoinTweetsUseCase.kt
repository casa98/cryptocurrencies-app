package com.casa98.currencies.domain.use_case.get_coin_tweets

import com.casa98.currencies.common.Resource
import com.casa98.currencies.data.remote.dto.CoinTweetDto
import com.casa98.currencies.domain.respository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinTweetsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<List<CoinTweetDto>>> = flow {
        try {
            emit(Resource.Loading<List<CoinTweetDto>>())
            val coinTweets = repository.getCoinTweets(coinId)
            emit(Resource.Success<List<CoinTweetDto>>(coinTweets))
        } catch (e: HttpException) {
            emit(Resource.Error<List<CoinTweetDto>>(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<CoinTweetDto>>(message = e.localizedMessage ?: "Couldn't reach server, check your internet connection"))
        }
    }
}