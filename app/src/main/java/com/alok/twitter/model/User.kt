package com.alok.twitter.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(
	@PrimaryKey
	val uid: UUID,
	val name: String,
	val handle: String,
	val avatar: ImageHolder = ImageHolder(),
	val verified: Boolean,
	val follower: Int,
	val following: Int
)