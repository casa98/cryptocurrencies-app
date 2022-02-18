package com.casa98.currencies.domain.use_case.get_coins

import com.casa98.currencies.common.Resource
import com.casa98.currencies.data.remote.dto.toCoin
import com.casa98.currencies.domain.respository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading(data = null))
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred", data = null))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Couldn't reach server, check your internet connection", data = null))
        }
    }
}