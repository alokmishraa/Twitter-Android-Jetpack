package com.alok.twitter

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.alok.twitter.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	repository: PostRepository
) : ViewModel() {
	lateinit var navHostController: NavHostController
	val postList = repository.getPosts()
	val userList = repository.getUsers()

	fun navigateTo(route: String) {
		navHostController.navigate(route)
	}
}
