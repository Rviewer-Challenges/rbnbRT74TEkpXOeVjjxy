package com.rumosoft.feature_timeline.sampledata

import com.rumosoft.feature_timeline.presentation.screen.model.TweetUI
import com.rumosoft.feature_timeline.presentation.screen.model.TweetUI.Companion.longFormatter
import com.rumosoft.feature_timeline.presentation.screen.model.formatToLocalDate
import java.time.Instant

object SampleScreenTweetData {
    fun sampleScreenTweet(): TweetUI =
        TweetUI(
            id = 1,
            profileImageUrl = "",
            username = "User Name",
            nickname = "nickname",
            message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            numComments = "62.9K",
            numRetweets = "92.6K",
            numLikes = "494.6K",
            shortElapsedTime = "3h",
            longElapsedTime = Instant.now().minusSeconds(3 * 3600L).formatToLocalDate(longFormatter),
            verified = true,
            images = emptyList(),
        )
}
