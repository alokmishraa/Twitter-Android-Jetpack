package com.alok.twitter.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alok.twitter.model.Post
import java.util.*

@Dao
interface PostDao {
	@Query("SELECT * FROM post")
	fun getAllPost(): LiveData<List<Post>>

	@Query("SELECT * FROM post WHERE uid = :uuid")
	fun getPost(uuid: UUID): LiveData<Post>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(posts: List<Post>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(post: Post)
}