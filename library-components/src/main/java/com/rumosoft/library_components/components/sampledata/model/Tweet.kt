package com.rumosoft.library_components.components.sampledata.model

import com.rumosoft.library_components.components.model.ImageUI

data class Tweet(
    val profileImageUrl: String,
    val username: String,
    val nickname: String,
    val message: String,
    val numComments: String,
    val numRetweets: String,
    val numLikes: String,
    val shortElapsedTime: String,
    val longElapsedTime: String,
    val images: List<ImageUI>,
)
