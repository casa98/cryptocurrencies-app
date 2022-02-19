package com.casa98.currencies.domain.use_case.get_coins

import com.casa98.currencies.common.Resource
import com.casa98.currencies.data.remote.dto.toCoin
import com.casa98.currencies.domain.model.Coin
import com.casa98.currencies.domain.respository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>(message = e.localizedMessage ?: "Couldn't reach server, check your internet connection"))
        }
    }
}