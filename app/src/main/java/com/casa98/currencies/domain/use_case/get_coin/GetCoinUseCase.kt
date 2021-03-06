package com.casa98.currencies.domain.use_case.get_coin

import com.casa98.currencies.common.Resource
import com.casa98.currencies.data.remote.dto.toCoinDetail
import com.casa98.currencies.domain.model.CoinDetail
import com.casa98.currencies.domain.respository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoin(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>(message = e.localizedMessage ?: "Couldn't reach server, check your internet connection"))
        }
    }
}