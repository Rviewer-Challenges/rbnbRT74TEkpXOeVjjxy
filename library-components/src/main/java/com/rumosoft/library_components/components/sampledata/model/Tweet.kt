package com.rumosoft.library_components.components.sampledata.model

data class Tweet(
    val profileImageUrl: String,
    val username: String,
    val nickname: String,
    val message: String,
    val numComments: String,
    val numRetweets: String,
    val numLikes: String,
    val elapsedTime: String,
    val images: List<String>,
)