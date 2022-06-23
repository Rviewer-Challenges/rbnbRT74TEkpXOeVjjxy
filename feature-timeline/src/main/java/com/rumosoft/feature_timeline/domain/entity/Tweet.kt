package com.rumosoft.feature_timeline.domain.entity

import java.time.Instant

data class Tweet(
    val id: Long,
    val username: String,
    val nickname: String,
    val profileImageUrl: String,
    val message: String,
    val numComments: Long,
    val numRetweets: Long,
    val numLikes: Long,
    val posted: Instant,
    val verified: Boolean,
    val images: List<TweetImage> = emptyList(),
)