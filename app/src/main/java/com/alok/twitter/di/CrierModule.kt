package com.alok.twitter.di

import android.content.Context
import com.alok.twitter.repository.PostRemoteDataSource
import com.alok.twitter.repository.PostRepository
import com.alok.twitter.repository.PostService
import com.alok.twitter.room.TweetDatabase
import com.alok.twitter.room.dao.PostDao
import com.alok.twitter.room.dao.UserDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TweetModule {
	@Singleton
	@Provides
	fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
		.baseUrl("https://down.ovh/Tweet/")
		.addConverterFactory(GsonConverterFactory.create(gson))
		.build()

	@Provides
	fun provideGson(): Gson = GsonBuilder().create()

	@Provides
	fun providePostService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)

	@Singleton
	@Provides
	fun providePostRemoteDataSource(postService: PostService) = PostRemoteDataSource(postService)

	@Singleton
	@Provides
	fun provideDatabase(@ApplicationContext appContext: Context) = TweetDatabase.getDatabase(appContext)

	@Singleton
	@Provides
	fun providePostDao(db: TweetDatabase) = db.postDao()

	@Singleton
	@Provides
	fun provideUserDao(db: TweetDatabase) = db.userDao()

	@Singleton
	@Provides
	fun provideRepository(
		remoteDataSource: PostRemoteDataSource,
		postLocalDataSource: PostDao,
		userLocalDataSource: UserDao
	) =
		PostRepository(remoteDataSource, postLocalDataSource, userLocalDataSource)
}