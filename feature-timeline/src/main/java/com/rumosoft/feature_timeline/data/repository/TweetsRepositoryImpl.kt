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
                ),
                Tweet(
                    username = "Brais Moure",
                    nickname = "MoureDev",
                    profileImageUrl = "https://pbs.twimg.com/profile_images/1532420458087337993/gzyQBPsL_400x400.jpg",
                    message = "Mañana hay concierto con @_nasch_ y @powerhdeleon \uD83E\uDD18\n" +
                            "\n" +
                            "Enlace al vídeo y horario:\n" +
                            "\uD83D\uDCFA https://youtu.be/Cp4LHJMPq3U",
                    numComments = 2,
                    numRetweets = 4,
                    numLikes = 33,
                    elapsedTime = "Jun 1",
                    verified = true
                ),
            )
        }
    }
}