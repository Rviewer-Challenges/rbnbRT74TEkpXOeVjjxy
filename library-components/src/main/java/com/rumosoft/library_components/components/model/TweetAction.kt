package com.rumosoft.library_components.components.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rumosoft.library_components.R

sealed class TweetAction(
    @DrawableRes val iconResource: Int,
    @StringRes val description: Int,
) {
    fun hasValue() = this !is TweetActionShare
}

sealed class TweetActionWithValue(
    @DrawableRes iconResource: Int,
    @StringRes description: Int,
    val value: String,
) :
    TweetAction(iconResource, description)



class TweetActionComments(
    value: String,
    @DrawableRes iconResource: Int = R.drawable.ic_comment_stroke,
    @StringRes description: Int = R.string.comments,
) :
    TweetActionWithValue(iconResource, description, value)

class TweetActionRetweet(
    value: String,
    @DrawableRes iconResource: Int = R.drawable.ic_retweet_stroke,
    @StringRes description: Int = R.string.retweets,
) :
    TweetActionWithValue(iconResource, description, value)

class TweetActionLike(
    value: String,
    iconResource: Int = R.drawable.ic_heart_stroke,
    @StringRes description: Int = R.string.likes,
) :
    TweetActionWithValue(iconResource, description, value)

class TweetActionShare(
    iconResource: Int = R.drawable.ic_share_stroke,
    @StringRes description: Int = R.string.shares,
) :
    TweetAction(iconResource, description)