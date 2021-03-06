package com.rumosoft.library_components.components.sampledata

import com.rumosoft.library_components.components.sampledata.model.Tweet

object SampleTweetData {
    fun sampleTweet(): Tweet =
        Tweet(
            profileImageUrl = "",
            username = "User Name",
            nickname = "nickname",
            message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            numComments = "62.9K",
            numRetweets = "52.6K",
            numQuoteTweets = "42.8K",
            totalRetweets = "95.4K",
            numLikes = "494.6K",
            shortElapsedTime = "3h",
            longElapsedTime = "19:50 · 01 Apr 22",
            images = emptyList(),
        )
}
