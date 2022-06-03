package com.rumosoft.library_components.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rumosoft.library_components.R
import com.rumosoft.library_components.components.sampledata.SampleTweetData
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun ProfileImage(
    profileImageUrl: String,
    profileName: String,
) {
    AsyncImage(
        model = profileImageUrl,
        placeholder = painterResource(R.drawable.ic_rounded_image_placeholder),
        contentDescription = profileName,
        modifier = Modifier
            .size(55.dp)
            .clip(CircleShape),
    )
}

@Preview(
    showBackground = true
)
@Composable
fun ProfileImagePreview() {
    val sampleTweet = remember { SampleTweetData.sampleTweet() }
    TwitterMirroringTheme {
        ProfileImage(
            profileImageUrl = sampleTweet.profileImageUrl,
            profileName = sampleTweet.username,
        )
    }
}
