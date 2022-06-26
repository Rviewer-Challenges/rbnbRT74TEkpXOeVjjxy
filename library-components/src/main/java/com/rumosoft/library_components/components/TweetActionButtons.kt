package com.rumosoft.library_components.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.components.model.TweetActionComments
import com.rumosoft.library_components.components.model.TweetActionLike
import com.rumosoft.library_components.components.model.TweetActionRetweet
import com.rumosoft.library_components.components.model.TweetActionShare
import com.rumosoft.library_components.components.model.TweetActionsClick
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun TweetActionButtons(
    numComments: String,
    numRetweets: String,
    numLikes: String,
    modifier: Modifier = Modifier,
    showValue: Boolean = true,
    onActionsClick: TweetActionsClick,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(end = 11.dp),
    ) {
        ActionButton(
            action = TweetActionComments(numComments),
            modifier = Modifier.weight(1f),
            showValue = showValue,
            onClick = onActionsClick::onCommentsClick,
        )
        ActionButton(
            action = TweetActionRetweet(numRetweets),
            modifier = Modifier.weight(1f),
            showValue = showValue,
            onClick = onActionsClick::onRetweetsClick,
        )
        ActionButton(
            action = TweetActionLike(numLikes),
            modifier = Modifier.weight(1f),
            showValue = showValue,
            onClick = onActionsClick::onLikesClick,
        )
        ActionButton(
            action = TweetActionShare(),
            modifier = Modifier.weight(1f),
            showValue = showValue,
            onClick = onActionsClick::onShareClick,
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TweetActionButtonsPreview() {
    TwitterMirroringTheme {
        TweetActionButtons(
            numLikes = "11",
            numRetweets = "22",
            numComments = "33",
            onActionsClick = object : TweetActionsClick {
                override fun onCommentsClick() {}
                override fun onRetweetsClick() {}
                override fun onLikesClick() {}
                override fun onShareClick() {}
            }
        )
    }
}