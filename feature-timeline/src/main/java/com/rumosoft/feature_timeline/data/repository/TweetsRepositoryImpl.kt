package com.rumosoft.feature_timeline.data.repository

import com.rumosoft.feature_timeline.domain.entity.Tweet
import com.rumosoft.feature_timeline.domain.repo_interfaces.TweetsRepository
import com.rumosoft.feature_timeline.infrastructure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TweetsRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : TweetsRepository {
    override suspend fun fetchTweets(): List<Tweet> {
        return withContext(dispatcher) {
            listOf(
                Tweet(
                    username = "Twitter",
                    nickname = "Twitter",
                    profileImageUrl = "https://pbs.twimg.com/profile_images/1354481096742768640/axJjcdix_400x400.jpg",
                    message = "You good?",
                    numComments = 62900,
                    numRetweets = 92600,
                    numLikes = 494600,
                    elapsedTime = "Mar 30",
                    verified = true
                )
            )
        }
    }
}