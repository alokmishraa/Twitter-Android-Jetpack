package com.alok.twitter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {
	lateinit var navHostController: NavHostController

	fun navigateTo(route: String) {
		navHostController.navigate(route)
	}
}
