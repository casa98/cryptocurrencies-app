package com.casa98.currencies.presentation.coins_screen.tag_info

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TagInfoBottomSheet(
    viewModel: TagInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = viewModel.selectedTag.value,
                style = MaterialTheme.typography.h5
            )
            state.tagInfo?.let { tagInfo ->
                Spacer(modifier = Modifier.height(16.dp))
                Text(tagInfo.description)
            }
        }

        if(state.isLoading) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        if(state.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}