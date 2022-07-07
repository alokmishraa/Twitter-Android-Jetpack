package com.alok.twitter.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alok.twitter.model.User
import java.util.*

@Dao
interface UserDao {
	@Query("SELECT * FROM user")
	fun getAllUser(): LiveData<List<User>>

	@Query("SELECT * FROM user WHERE uid = :uuid")
	fun getUser(uuid: UUID): LiveData<User>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(users: List<User>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(user: User)
}