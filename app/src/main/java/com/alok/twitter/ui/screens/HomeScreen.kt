package com.alok.twitter.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alok.twitter.MainViewModel
import com.alok.twitter.data.DummyData.getUserFromUID
import com.alok.twitter.model.Post
import com.alok.twitter.model.User
import com.alok.twitter.ui.components.PostLayout


@Composable
fun HomeScreen(padding: PaddingValues = PaddingValues(), postList: List<Post>, userList: List<User>, viewModel: MainViewModel) {
	val lazyListState = rememberLazyListState()

	Box(
		Modifier
			.fillMaxSize()
			.padding(padding)
	) {
		LazyColumn(state = lazyListState) {
			items(postList) { post ->
				PostLayout(post, userList.getUserFromUID(post.userUID), viewModel)
				Divider()
			}
		}
	}
}