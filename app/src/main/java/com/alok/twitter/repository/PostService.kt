package com.alok.twitter.repository

import com.alok.twitter.model.Post
import com.alok.twitter.model.User
import retrofit2.Response
import retrofit2.http.GET

interface PostService {
	@GET("posts.json")
	suspend fun getAllPosts(): Response<List<Post>>

	@GET("users.json")
	suspend fun getAllUsers(): Response<List<User>>
}