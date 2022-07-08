package com.alok.twitter.model.service.module

import com.alok.twitter.ui.feeds.data.TweetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideTweetsRepository(): TweetsRepository {
        return TweetsRepository()
    }
}