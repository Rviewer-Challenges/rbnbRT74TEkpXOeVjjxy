package com.rumosoft.library_components.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.components.model.TweetAction
import com.rumosoft.library_components.components.model.TweetActionComments
import com.rumosoft.library_components.components.model.TweetActionWithValue
import com.rumosoft.library_components.presentation.theme.ClearRippleTheme
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme.colors
import kotlin.random.Random

@Composable
fun ActionButton(
    action: TweetAction,
    modifier: Modifier = Modifier,
    showValue: Boolean = true,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val tintColor = if (interactionSource.collectIsPressedAsState().value) colors.onSecondary
    else colors.onBackground
    CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
        Row(
            modifier = modifier
                .height(40.dp)
                .padding(start = 11.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (showValue) Arrangement.Start else Arrangement.Center
        ) {
            TextButton(
                contentPadding = PaddingValues(0.dp),
                onClick = onClick,
                interactionSource = interactionSource
            ) {
                Icon(
                    painter = painterResource(id = action.iconResource),
                    contentDescription = stringResource(id = action.description),
                    tint = tintColor,
                    modifier = Modifier
                        .size(18.dp),
                )
                if (showValue && action is TweetActionWithValue && action.value.isNotEmpty()) {
                    Text(
                        text = action.value,
                        style = TwitterMirroringTheme.typography.caption,
                        modifier = Modifier.padding(start = 8.dp),
                        color = tintColor,
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ActionButtonPreview() {
    TwitterMirroringTheme {
        val numComments = remember { Random.nextInt(1, 100) }
        ActionButton(
            action = TweetActionComments(numComments.toString()),
        )
    }
}
