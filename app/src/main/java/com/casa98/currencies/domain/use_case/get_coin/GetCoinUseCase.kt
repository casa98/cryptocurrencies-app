package com.casa98.currencies.domain.use_case.get_coin

import com.casa98.currencies.common.Resource
import com.casa98.currencies.data.remote.dto.toCoinDetail
import com.casa98.currencies.domain.respository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String) = flow {
        try {
            emit(Resource.Loading(data = null ))
            val coin = repository.getCoin(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred", data = null))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Couldn't reach server, check your internet connection", data = null))
        }
    }
}