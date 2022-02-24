package com.casa98.currencies.presentation.tag_info

import com.casa98.currencies.data.remote.dto.TagInfoDto

data class TagInfoState(
    val isLoading: Boolean = false,
    val tagInfo: TagInfoDto? = null,
    val error: String = "",
)
