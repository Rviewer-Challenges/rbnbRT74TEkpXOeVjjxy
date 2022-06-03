package com.rumosoft.library_components.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.components.model.TweetAction
import com.rumosoft.library_components.components.model.TweetActionComments
import com.rumosoft.library_components.components.model.TweetActionLike
import com.rumosoft.library_components.components.model.TweetActionRetweet
import com.rumosoft.library_components.components.model.TweetActionShare
import com.rumosoft.library_components.components.model.TweetActionWithValue
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun TweetActionButtons(
    numComments: Int,
    numRetweets: Int,
    numLikes: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        ActionButton(
            action = TweetActionComments(numComments),
            modifier = Modifier.weight(1f),
        )
        ActionButton(
            action = TweetActionRetweet(numRetweets),
            modifier = Modifier.weight(1f),
        )
        ActionButton(
            action = TweetActionLike(numLikes),
            modifier = Modifier.weight(1f),
        )
        ActionButton(
            action = TweetActionShare(),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun ActionButton(
    action: TweetAction,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .padding(horizontal = 11.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = action.iconResource),
            contentDescription = stringResource(id = action.description),
            modifier = Modifier
                .size(18.dp)
        )
        if (action is TweetActionWithValue) {
            Text(
                action.value.toString(),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TweetActionButtonsPreview() {
    TwitterMirroringTheme {
        TweetActionButtons(
            numLikes = 11,
            numRetweets = 22,
            numComments = 33,
        )
    }
}