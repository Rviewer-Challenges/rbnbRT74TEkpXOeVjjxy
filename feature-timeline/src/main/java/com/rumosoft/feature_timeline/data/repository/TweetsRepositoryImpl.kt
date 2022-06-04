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
                    username = "Jep",
                    nickname = "jeprubio",
                    profileImageUrl = "https://via.placeholder.com/50/0000FF/FFFFFF?Text=Jep",
                    message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tincidunt, metus et aliquam interdum, odio lacus commodo mauris, nec tristique velit eros sit amet tortor. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin neque risus, mollis eu scelerisque ac, tincidunt id elit. Etiam condimentum massa sed felis congue blandit. Pellentesque suscipit erat posuere gravida elementum. Donec at consequat lectus. Quisque sed lectus placerat, bibendum orci ut, porta leo. Donec sed nulla sapien. Pellentesque ante nibh, vestibulum sit amet dictum id, iaculis ut sapien. Donec eleifend, nisl et convallis bibendum, ligula purus iaculis felis, a luctus neque ex at ex. Integer luctus sapien non ante egestas, sit amet gravida odio congue. Fusce nec lacus lorem.",
                    numComments = 3,
                    numRetweets = 2,
                    numLikes = 1,
                    elapsedTime = "1m",
                    verified = true
                )
            )
        }
    }
}