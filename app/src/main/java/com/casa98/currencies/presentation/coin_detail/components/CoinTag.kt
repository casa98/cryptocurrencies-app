package com.casa98.currencies.presentation.coin_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinTag(
    onTagClicked: (tag: String) -> Unit,
    tag: String
) {
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.border(
            width = 1.dp,
            color = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(30.dp),
        ).clip(RoundedCornerShape(30.dp))
            .clickable {
                onTagClicked(tag)
            }
    ) {
        Text(
            text = tag,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(10.dp)
        )
    }
}