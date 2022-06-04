package com.rumosoft.feature_timeline.domain.entity

data class Tweet(
    val username: String,
    val nickname: String,
    val profileImageUrl: String,
    val message: String,
    val numComments: Int,
    val numRetweets: Int,
    val numLikes: Int,
    val elapsedTime: String,
    val verified: Boolean,
)