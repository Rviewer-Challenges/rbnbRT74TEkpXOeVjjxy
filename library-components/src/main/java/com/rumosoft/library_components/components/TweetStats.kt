package com.rumosoft.library_components.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun ColumnScope.TweetStats(stats: List<Pair<String, String>>) {
    Divider(
        color = TwitterMirroringTheme.colors.secondaryVariant,
        thickness = 1.dp,
        modifier = Modifier
            .padding(top = TwitterMirroringTheme.paddings.medium)
            .padding(horizontal = 11.dp)
    )
    Row {
        stats.forEach { stat ->
            Stat(stat)
        }
    }
    Divider(
        color = TwitterMirroringTheme.colors.secondaryVariant,
        thickness = 1.dp,
        modifier = Modifier
            .padding(horizontal = 11.dp)
    )
}

@Composable
private fun Stat(stat: Pair<String, String>) {
    Row(
        modifier = Modifier
            .padding(start = 11.dp)
            .clickable { }
            .padding(vertical = 11.dp),
    ) {
        Text(
            text = stat.first,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stat.second,
            modifier = Modifier.padding(
                start = TwitterMirroringTheme.paddings.tinyPadding,
            )
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TweetStatsPreview() {
    TwitterMirroringTheme {
        Column {
            TweetStats(
                listOf(
                    "22" to "Retweets",
                    "33" to "Likes",
                )
            )
        }
    }
}
