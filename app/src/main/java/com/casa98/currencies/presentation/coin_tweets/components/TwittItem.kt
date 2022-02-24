package com.casa98.currencies.presentation.coin_tweets.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.casa98.currencies.common.extensions.toPresentationDate
import com.casa98.currencies.data.remote.dto.CoinTweetDto

@Composable
fun TwittItem(
    twitt: CoinTweetDto
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "@${twitt.userName}",
                color = MaterialTheme.colors.primary
            )
            Text(
                text = twitt.date.toPresentationDate(),
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = twitt.status)
        Spacer(modifier = Modifier.height(8.dp))
    }
}