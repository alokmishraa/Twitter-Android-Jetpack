package com.alok.twitter.repository

import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
	private val postService: PostService
) : BaseDataSource() {
	suspend fun getUsers() = getResult { postService.getAllUsers() }
	suspend fun getPosts() = getResult { postService.getAllPosts() }
}