package com.alok.twitter.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Post(
	@PrimaryKey
	@NonNull
	val uid: UUID = UUID.randomUUID(),
	val userUID: UUID,
	val content: String,
	val timestamp: Date = Date(),
	val likeCount: Int,
	val reblogCount: Int,
	val commentsCount: Int,
	val liked: Boolean,
	val reblogged: Boolean,
	val imageList: List<ImageHolder> = listOf()
)