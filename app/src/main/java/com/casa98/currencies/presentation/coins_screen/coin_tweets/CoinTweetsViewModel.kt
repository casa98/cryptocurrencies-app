package com.casa98.currencies.presentation.coins_screen.coin_tweets

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.casa98.currencies.common.Resource
import com.casa98.currencies.domain.use_case.get_coin_tweets.GetCoinTweetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinTweetsViewModel @Inject constructor(
    private val getCoinTweetsUseCase: GetCoinTweetsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(CoinTweetsState())
    val state: State<CoinTweetsState> = _state

    private val _appBarTitle = mutableStateOf<String>("")
    val appBarTitle: State<String> = _appBarTitle

    init {
        savedStateHandle.get<String>("coinId")?.let { coinId ->
            _appBarTitle.value = coinId
            getCoinTweets(coinId)
        }
    }

    private fun getCoinTweets(coinId: String) {
        getCoinTweetsUseCase(coinId).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _state.value = CoinTweetsState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinTweetsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinTweetsState(coinTweets = result.data ?: emptyList() )
                }
            }
        }.launchIn(viewModelScope)
    }
}