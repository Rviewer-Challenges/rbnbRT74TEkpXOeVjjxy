package com.rumosoft.library_components.components.sampledata.model

data class Tweet(
    val profileImageUrl: String,
    val username: String,
    val nickname: String,
    val message: String,
    val numComments: Int,
    val numRetweets: Int,
    val numLikes: Int,
    val elapsedTime: String
)