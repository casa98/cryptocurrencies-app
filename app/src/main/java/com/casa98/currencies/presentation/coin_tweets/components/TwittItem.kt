package com.casa98.currencies.presentation.coin_tweets.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.casa98.currencies.data.remote.dto.CoinTweetDto
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun TwittItem(
    twitt: CoinTweetDto
) {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val todayDate = format.parse(SimpleDateFormat("yyyy-MM-dd'T'00:00:00'Z'").format(Date()))
    val twittDate = format.parse(twitt.date)
    val date = if(twittDate!!.before(todayDate)) {
        SimpleDateFormat("MMM dd, yyyy").format(twittDate)
    } else {
        SimpleDateFormat("h:mm a").format(twittDate)
    }

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
                text = date,
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = twitt.status)
        Spacer(modifier = Modifier.height(8.dp))
    }
}