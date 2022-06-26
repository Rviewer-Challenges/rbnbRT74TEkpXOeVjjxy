package com.rumosoft.feature_timeline.domain.usecase

import com.rumosoft.feature_timeline.domain.entity.Tweet
import com.rumosoft.feature_timeline.domain.repo_interfaces.TweetsRepository
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: TweetsRepository
) {
    suspend operator fun invoke(tweetId: Long): List<Tweet> =
        repository.fetchComments(tweetId)
}
