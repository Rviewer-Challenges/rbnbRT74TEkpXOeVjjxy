package com.rumosoft.feature_timeline.data.repository

import com.rumosoft.feature_timeline.domain.entity.ImageType.Gif
import com.rumosoft.feature_timeline.domain.entity.ImageType.Video
import com.rumosoft.feature_timeline.domain.entity.Tweet
import com.rumosoft.feature_timeline.domain.entity.TweetImage
import com.rumosoft.feature_timeline.domain.repo_interfaces.TweetsRepository
import com.rumosoft.feature_timeline.infrastructure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject
import kotlin.random.Random

class TweetsRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : TweetsRepository {
    override suspend fun fetchTweets(): List<Tweet> {
        return withContext(dispatcher) {
            fakeTweetData()
        }
    }

    override suspend fun fetchTweet(tweetId: Long): Tweet {
        return withContext(dispatcher) {
            fakeTweetData().first {
                it.id == tweetId
            }
        }
    }

    override suspend fun fetchComments(tweetId: Long): List<Tweet> {
        return withContext(dispatcher) {
            fakeCommentsData(tweetId)
        }
    }

    private fun fakeTweetData() = listOf(
        Tweet(
            id = 1,
            username = "Twitter",
            nickname = "Twitter",
            profileImageUrl = "https://pbs.twimg.com/profile_images/1354481096742768640/axJjcdix_400x400.jpg",
            message = "You good?",
            numComments = 62900,
            numRetweets = 42900,
            numQuoteTweets = 49700,
            numLikes = 496000,
            posted = dateTimeToInstant("2021-04-01", "17:50"),
            verified = true,
        ),
        Tweet(
            id = 2,
            username = "Brais Moure",
            nickname = "MoureDev",
            profileImageUrl = "https://yt3.ggpht.com/ytc/AKedOLRyratMxgvHzoL-15qIZAGXjdALPmEe4jybJhd4MA=s176-c-k-c0x00ffffff-no-rj",
            message = "Mañana hay concierto con @_nasch_ y @powerhdeleon \uD83E\uDD18\n" +
                    "\n" +
                    "Enlace al vídeo y horario:\n" +
                    "\uD83D\uDCFA https://youtu.be/Cp4LHJMPq3U",
            numComments = 2,
            numRetweets = 3,
            numQuoteTweets = 1,
            numLikes = 33,
            posted = dateTimeToInstant("2021-06-01", "21:09"),
            verified = true,
        ),
        Tweet(
            id = 3,
            username = "I Am Devloper",
            nickname = "iamdevloper",
            profileImageUrl = "https://pbs.twimg.com/profile_images/1178631635606151168/yIlrcg4o_200x200.jpg",
            message = "\uD83D\uDCB0 http://configure8.io\n" +
                    "Colleague: why do you have 23 browser tabs open?\n" +
                    "Me:",
            numComments = 11,
            numRetweets = 70,
            numQuoteTweets = 5,
            numLikes = 764,
            posted = dateTimeToInstant("2021-06-02", "22:51"),
            verified = false,
            images = listOf(
                TweetImage(
                    id = 1L,
                    url = "https://pbs.twimg.com/media/FUSD8YeUYAEvhol?format=jpg",
                )
            )
        ),
        Tweet(
            id = 4,
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
            numComments = 48,
            numRetweets = 10,
            numQuoteTweets = 1,
            numLikes = 148,
            posted = dateTimeToInstant("2021-04-30", "12:51"),
            verified = false,
            images = listOf(
                TweetImage(
                    id = 2L,
                    url = "https://pbs.twimg.com/media/FRl-YBHWUAALC71?format=jpg"
                ),
                TweetImage(
                    id = 3L,
                    url = "https://pbs.twimg.com/media/FRl-YBPXwAAcodj?format=jpg"
                ),
            )
        ),
        Tweet(
            id = 5,
            username = "Carol",
            nickname = "carolmusyoka_",
            profileImageUrl = "https://pbs.twimg.com/profile_images/1535940679595171840/2T0-ouUT_200x200.jpg",
            message = "Been working on something exciting with #JetpackCompose \uD83D\uDE0E. It's still a work in progress. \n" +
                    "\uD83D\uDCF8 credit by @lets_drift",
            numComments = 12,
            numRetweets = 16,
            numQuoteTweets = 1,
            numLikes = 108,
            posted = dateTimeToInstant("2021-06-06", "10:43"),
            verified = false,
            images = listOf(
                TweetImage(
                    id = 4L,
                    url = "https://pbs.twimg.com/media/FUkBNmIWYAEZcBX?format=jpg"
                ),
                TweetImage(
                    id = 5L,
                    url = "https://pbs.twimg.com/media/FUkBNmNXEAA-Ouo?format=jpg"
                ),
                TweetImage(
                    id = 6L,
                    url = "https://pbs.twimg.com/media/FUkBPaDXEAAL3US?format=jpg"
                ),
            )
        ),
        Tweet(
            id = 6,
            username = "Rebecca Franks",
            nickname = "riggaroo",
            profileImageUrl = "https://pbs.twimg.com/profile_images/1523670472608686080/DQihW82K_200x200.jpg",
            message = "Today was fun! Android themed mini golf and discussions on Compose \uD83D\uDC4C #AndroidRoadshow",
            numComments = 0,
            numRetweets = 0,
            numQuoteTweets = 0,
            numLikes = 20,
            posted = dateTimeToInstant("2021-05-26", "19:16"),
            verified = false,
            images = listOf(
                TweetImage(
                    id = 7L,
                    url = "https://pbs.twimg.com/media/FTtP2u5XwAc5EoE?format=jpg"
                ),
                TweetImage(
                    id = 8L,
                    url = "https://pbs.twimg.com/media/FTtP3xmWUAMA1t1?format=jpg"
                ),
                TweetImage(
                    id = 9L,
                    url = "https://pbs.twimg.com/media/FTtP3PQXoAARcnF?format=jpg"
                ),
                TweetImage(
                    id = 10L,
                    url = "https://pbs.twimg.com/media/FTtP4PKXoAA_H-M?format=jpg"
                ),
            )
        ),
        Tweet(
            id = 7,
            username = "Jep Rubio",
            nickname = "jeprubio",
            profileImageUrl = "https://pbs.twimg.com/profile_images/1465660161939460100/8LJftrt6_200x200.jpg",
            message = "\uD83E\uDD16",
            numComments = 0,
            numRetweets = 1,
            numQuoteTweets = 0,
            numLikes = 2,
            posted = dateTimeToInstant("2021-06-05", "11:20"),
            verified = false,
            images = listOf(
                TweetImage(
                    id = 11L,
                    url = "https://c.tenor.com/vW__kIdOl0EAAAAC/android-google-android.gif",
                    imageType = Gif
                ),
            )
        ),
        Tweet(
            id = 8,
            username = "Jep Rubio",
            nickname = "jeprubio",
            profileImageUrl = "https://pbs.twimg.com/profile_images/1465660161939460100/8LJftrt6_200x200.jpg",
            message = "Sample Video",
            numComments = 1,
            numRetweets = 1,
            numQuoteTweets = 1,
            numLikes = 1,
            posted = dateTimeToInstant("2021-06-05", "15:20"),
            verified = false,
            images = listOf(
                TweetImage(
                    id = 11L,
                    url = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    time = "PT2M6S",
                    imageType = Video,
                ),
            )
        ),
    )

    private fun fakeCommentsData(tweetId: Long): List<Tweet> {
        return (1..tweetId).map {
            Tweet(
                id = (100 + it).toLong(),
                username = "Comment $it",
                nickname = "nickname $it",
                profileImageUrl = "",
                message = "comment $it",
                numComments = Random.nextInt(0, 10).toLong(),
                numRetweets = Random.nextInt(0, 10).toLong(),
                numQuoteTweets = Random.nextInt(0, 10).toLong(),
                numLikes = Random.nextInt(0, 10).toLong(),
                posted = dateTimeToInstant("2021-05-0$it", "0$it:15"),
                verified = Random.nextBoolean(),
            )
        }
    }

    private fun dateTimeToInstant(strDate: String, strTime: String): Instant {
        val strDateTime = strDate + "T" + strTime
        val ldt: LocalDateTime = LocalDateTime.parse(strDateTime)
        return ldt.atOffset(ZoneOffset.UTC).toInstant()
    }
}