package com.casa98.currencies.presentation.coin_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.casa98.currencies.presentation.Screen
import com.casa98.currencies.presentation.coin_detail.components.CoinTag
import com.casa98.currencies.presentation.coin_detail.components.TeamListItem
import com.casa98.currencies.presentation.tag_info.TagInfoBottomSheet
import com.casa98.currencies.presentation.tag_info.TagInfoViewModel
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinDetailScreen(
    navController: NavController,
    viewModel: CoinDetailViewModel = hiltViewModel(),
    tagViewModel: TagInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val bottomState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomState,
        scrimColor = if (isSystemInDarkTheme()) MaterialTheme.colors.background.copy(alpha = 0.5f) else MaterialTheme.colors.onSurface.copy(alpha = 0.32f),
        sheetContent = { TagInfoBottomSheet() }
    ){
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            color = if (coin.isActive) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "See Twitter updates for ${coin.name}",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.body1.copy(
                            textDecoration = TextDecoration.Underline,
                        ),
                        modifier = Modifier
                            .clickable {
                                navController.navigate("${Screen.CoinTweetsScreen.route}/${coin.coinId}")
                            }
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = coin.description,
                        style = MaterialTheme.typography.body2,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h5,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags?.forEach { tag ->
                            CoinTag(tag = tag, onTagClicked = { selectedTag ->
                                tagViewModel.saveSelectedTag(selectedTag)
                                coroutineScope.launch { bottomState.show() }
                            })
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    if(coin.teamMember.isNotEmpty()) {
                        Text(
                            text = "Team Members",
                            style = MaterialTheme.typography.h5,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                items(coin.teamMember) {  teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }
        }

        // If it's still loading:
        if(state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // If there are errors:
        if(state.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}