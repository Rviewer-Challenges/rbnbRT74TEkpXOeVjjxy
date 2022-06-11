package com.rumosoft.feature_timeline.domain.repo_interfaces

import com.rumosoft.feature_timeline.domain.entity.Tweet

interface TweetsRepository {
    suspend fun fetchTweets(): List<Tweet>
    suspend fun fetchTweet(tweetId: Long): Tweet
}