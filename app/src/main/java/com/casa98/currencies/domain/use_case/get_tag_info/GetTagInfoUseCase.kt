package com.casa98.currencies.domain.use_case.get_tag_info

import com.casa98.currencies.common.Resource
import com.casa98.currencies.data.remote.dto.TagInfoDto
import com.casa98.currencies.domain.respository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTagInfoUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(tag: String): Flow<Resource<TagInfoDto>> = flow {
        try {
            emit(Resource.Loading<TagInfoDto>())
            val tagInfo = repository.getTagInfo(tag)
            emit(Resource.Success<TagInfoDto>(tagInfo))
        } catch (e: HttpException) {
            emit(Resource.Error<TagInfoDto>(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<TagInfoDto>(message = e.localizedMessage ?: "Couldn't reach server, check your internet connection"))
        }
    }
}