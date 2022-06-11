package com.rumosoft.feature_timeline.data.repository

import com.rumosoft.feature_timeline.domain.entity.ImageType.Gif
import com.rumosoft.feature_timeline.domain.entity.Tweet
import com.rumosoft.feature_timeline.domain.entity.TweetImage
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
                    elapsedTime = "30 Mar",
                    verified = true,
                ),
                Tweet(
                    username = "Brais Moure",
                    nickname = "MoureDev",
                    profileImageUrl = "https://pbs.twimg.com/profile_images/1534960134937530376/8ty6kbOF_200x200.jpg",
                    message = "Mañana hay concierto con @_nasch_ y @powerhdeleon \uD83E\uDD18\n" +
                            "\n" +
                            "Enlace al vídeo y horario:\n" +
                            "\uD83D\uDCFA https://youtu.be/Cp4LHJMPq3U",
                    numComments = 2,
                    numRetweets = 4,
                    numLikes = 33,
                    elapsedTime = "1 Jun",
                    verified = true,
                ),
                Tweet(
                    username = "I Am Devloper",
                    nickname = "iamdevloper",
                    profileImageUrl = "https://pbs.twimg.com/profile_images/1178631635606151168/yIlrcg4o_200x200.jpg",
                    message = "\uD83D\uDCB0 http://configure8.io\n" +
                            "Colleague: why do you have 23 browser tabs open?\n" +
                            "Me:",
                    numComments = 10,
                    numRetweets = 70,
                    numLikes = 733,
                    elapsedTime = "3 Jun",
                    verified = false,
                    images = listOf(
                        TweetImage(
                            url = "https://pbs.twimg.com/media/FUSD8YeUYAEvhol?format=jpg",
                        )
                    )
                ),
                Tweet(
                    username = "Eric Ampire",
                    nickname = "eric_ampire",
                    profileImageUrl = "https://pbs.twimg.com/profile_images/1447119122953822211/BwIc6oDx_200x200.jpg",
                    message = "After reading these amazing books \"Android UI Development with Compose\" by " +
                            "@tkuenneth, and \"Simplifying Application Development with KMM\" by " +
                            "@nrobir, I’m giving away 4 copies of these books (2 copies per book) \n" +
                            "\n" +
                            "To enter:\n" +
                            "✅ Like\n" +
                            "✅ Follow me\n" +
                            "✅ Reply which book you want",
                    numComments = 32,
                    numRetweets = 363,
                    numLikes = 3265,
                    elapsedTime = "30 Apr",
                    verified = false,
                    images = listOf(
                        TweetImage("https://pbs.twimg.com/media/FRl-YBHWUAALC71?format=jpg"),
                        TweetImage("https://pbs.twimg.com/media/FRl-YBPXwAAcodj?format=jpg"),
                    )
                ),
                Tweet(
                    username = "Carol",
                    nickname = "carolmusyoka_",
                    profileImageUrl = "https://pbs.twimg.com/profile_images/1530554079268818944/JCnbcEVP_200x200.jpg",
                    message = "Been working on something exciting with #JetpackCompose \uD83D\uDE0E. It's still a work in progress. \n" +
                            "\uD83D\uDCF8 credit by @lets_drift",
                    numComments = 9,
                    numRetweets = 14,
                    numLikes = 87,
                    elapsedTime = "6 Jun",
                    verified = false,
                    images = listOf(
                        TweetImage("https://pbs.twimg.com/media/FUkBNmIWYAEZcBX?format=jpg"),
                        TweetImage("https://pbs.twimg.com/media/FUkBNmNXEAA-Ouo?format=jpg"),
                        TweetImage("https://pbs.twimg.com/media/FUkBPaDXEAAL3US?format=jpg"),
                    )
                ),
                Tweet(
                    username = "Rebecca Franks",
                    nickname = "riggaroo",
                    profileImageUrl = "https://pbs.twimg.com/profile_images/1523670472608686080/DQihW82K_200x200.jpg",
                    message = "Today was fun! Android themed mini golf and discussions on Compose \uD83D\uDC4C #AndroidRoadshow",
                    numComments = 0,
                    numRetweets = 0,
                    numLikes = 20,
                    elapsedTime = "26 May",
                    verified = false,
                    images = listOf(
                        TweetImage("https://pbs.twimg.com/media/FTtP2u5XwAc5EoE?format=jpg"),
                        TweetImage("https://pbs.twimg.com/media/FTtP3xmWUAMA1t1?format=jpg"),
                        TweetImage("https://pbs.twimg.com/media/FTtP3PQXoAARcnF?format=jpg"),
                        TweetImage("https://pbs.twimg.com/media/FTtP4PKXoAA_H-M?format=jpg"),
                    )
                ),
                Tweet(
                    username = "Jep Rubio",
                    nickname = "jeprubio",
                    profileImageUrl = "https://pbs.twimg.com/profile_images/1465660161939460100/8LJftrt6_200x200.jpg",
                    message = "\uD83E\uDD16",
                    numComments = 0,
                    numRetweets = 1,
                    numLikes = 2,
                    elapsedTime = "5 Jun",
                    verified = false,
                    images = listOf(
                        TweetImage(
                            url = "https://c.tenor.com/vW__kIdOl0EAAAAC/android-google-android.gif",
                            imageType = Gif
                        ),
                    )
                ),
            )
        }
    }
}