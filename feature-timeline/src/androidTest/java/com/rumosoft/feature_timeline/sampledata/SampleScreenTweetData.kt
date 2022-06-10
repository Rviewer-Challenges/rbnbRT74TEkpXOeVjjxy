package com.rumosoft.feature_timeline.sampledata

import com.rumosoft.feature_timeline.presentation.screen.model.ScreenTweet

object SampleScreenTweetData {
    fun sampleScreenTweet(): ScreenTweet =
        ScreenTweet(
            profileImageUrl = "",
            username = "User Name",
            nickname = "nickname",
            message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            numComments = "62.9K",
            numRetweets = "92.6K",
            numLikes = "494.6K",
            elapsedTime = "3h",
            verified = true,
        )
}
