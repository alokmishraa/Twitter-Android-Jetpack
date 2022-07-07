package com.alok.twitter.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alok.twitter.model.Post
import com.alok.twitter.model.User
import com.alok.twitter.room.converter.DateConverter
import com.alok.twitter.room.converter.ImageHolderConverter
import com.alok.twitter.room.converter.UUIDConverter
import com.alok.twitter.room.dao.PostDao
import com.alok.twitter.room.dao.UserDao

@Database(entities = [Post::class, User::class], version = 1, exportSchema = false)
@TypeConverters(UUIDConverter::class, DateConverter::class, ImageHolderConverter::class)
abstract class TweetDatabase : RoomDatabase() {

	abstract fun postDao(): PostDao
	abstract fun userDao(): UserDao

	companion object {
		@Volatile
		private var instance: TweetDatabase? = null

		fun getDatabase(context: Context): TweetDatabase =
			instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

		private fun buildDatabase(appContext: Context) =
			Room.databaseBuilder(appContext, TweetDatabase::class.java, "Tweet")
				.fallbackToDestructiveMigration()
				.build()
	}

}