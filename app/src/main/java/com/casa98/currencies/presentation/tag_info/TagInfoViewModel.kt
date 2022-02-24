package com.casa98.currencies.presentation.tag_info

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.casa98.currencies.common.Resource
import com.casa98.currencies.domain.use_case.get_tag_info.GetTagInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TagInfoViewModel @Inject constructor(
    private val getTagInfoUseCase: GetTagInfoUseCase
): ViewModel() {

    private val _state = mutableStateOf(TagInfoState())
    val state: State<TagInfoState> = _state

    private val _selectedTag = mutableStateOf("")
    val selectedTag: State<String> = _selectedTag

    fun saveSelectedTag(tag: String) {
        _selectedTag.value = tag
        getTagInfo(tag)
    }

    private fun getTagInfo(tag: String) {
        getTagInfoUseCase(tag).onEach { result ->
            when(result){
                is Resource.Error -> {
                    _state.value = TagInfoState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = TagInfoState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = TagInfoState(tagInfo = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}