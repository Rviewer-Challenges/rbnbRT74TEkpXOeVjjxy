package com.rumosoft.feature_timeline.infrastructure.di

import com.rumosoft.feature_timeline.data.repository.TweetsRepositoryImpl
import com.rumosoft.feature_timeline.domain.repo_interfaces.TweetsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTweetsRepository(
        tweetsRepository: TweetsRepositoryImpl
    ): TweetsRepository
}
