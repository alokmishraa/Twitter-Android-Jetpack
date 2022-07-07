package com.alok.twitter.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.alok.twitter.data.DummyData
import com.alok.twitter.theme.TweetSocialTheme

@Composable
fun TopBar(
	modifier: Modifier = Modifier,
	onNavIconPressed: () -> Unit = {},
	onActionIconPressed: () -> Unit = {}
) {
	Column {
		TopAppBar(
			backgroundColor = MaterialTheme.colors.surface,
			contentColor = MaterialTheme.colors.onSurface,
			modifier = modifier,
			elevation = 0.dp,
		) {
			Box(
				Modifier
					.height(32.dp)
					.fillMaxWidth()
			) {
				val user = DummyData.currentUser

				// LEFT NAVIGATION
				Row(
					Modifier
						.fillMaxHeight()
						.align(Alignment.CenterStart)
				) {
					IconButton(
						onClick = { onNavIconPressed() },
						enabled = true,
					) {
						Image(
							painter = rememberImagePainter(
								data = user.avatar.getDataForPainter(),
								builder = {
									crossfade(true)
									transformations(CircleCropTransformation())
								}
							),
							"",
							modifier = Modifier
								.size(32.dp),
							contentScale = ContentScale.Crop
						)
					}
				}

				// TITLE
				Row(
					Modifier
						.fillMaxHeight()
						.align(Alignment.Center)
				) {
					Icon(Icons.Filled.Campaign, "TODO", tint = MaterialTheme.colors.primary, modifier = Modifier.size(32.dp))
				}

				// ACTIONS
				Row(
					Modifier
						.fillMaxHeight()
						.align(Alignment.CenterEnd),
					content = {
						IconButton(
							onClick = { onActionIconPressed() },
							enabled = true,
						) {
							Icon(Icons.Outlined.AutoAwesome, "TODO", modifier = Modifier.size(32.dp))
						}
					}
				)
			}
		}
		Divider()
	}
}

@Composable
fun TopBarExtra(
	modifier: Modifier = Modifier,
	onNavIconPressed: () -> Unit = {},
	onActionIconPressed: () -> Unit = {},
	title: String
) {
	Column {
		TopAppBar(
			backgroundColor = MaterialTheme.colors.surface,
			contentColor = MaterialTheme.colors.onSurface,
			modifier = modifier,
			elevation = 0.dp,
		) {
			Box(
				Modifier
					.height(32.dp)
					.fillMaxWidth()
			) {
				val user = DummyData.currentUser

				// LEFT NAVIGATION
				Row(
					Modifier
						.fillMaxHeight()
						.align(Alignment.CenterStart)
				) {
					IconButton(
						onClick = { onNavIconPressed() },
						enabled = true,
					) {
						Image(
							painter = rememberImagePainter(
								data = user.avatar.getDataForPainter(),
								builder = {
									crossfade(true)
									transformations(CircleCropTransformation())
								}
							),
							"",
							modifier = Modifier
								.size(32.dp),
							contentScale = ContentScale.Crop
						)
					}
					Text(
						text = title,
						modifier = Modifier.padding(start = 16.dp, top = 4.dp),
						style = MaterialTheme.typography.h6,
						color = MaterialTheme.colors.onSurface,
						fontWeight = FontWeight.Bold
					)
				}

				// TITLE
				Row(
					Modifier
						.fillMaxHeight()
						.align(Alignment.Center)
				) {

				}

				// ACTIONS
				Row(
					Modifier
						.fillMaxHeight()
						.align(Alignment.CenterEnd),
					content = {
						IconButton(
							onClick = { onActionIconPressed() },
							enabled = true,
						) {
							Icon(Icons.Outlined.AutoAwesome, "TODO", modifier = Modifier.size(32.dp))
						}
					}
				)
			}
		}
		Divider()
	}
}

// ---------------------------------------------------------------
// COMPOSE PREVIEW
// ---------------------------------------------------------------
@Preview
@Composable
fun TopBarPreview() {
	TweetSocialTheme(darkTheme = false) {
		TopBar()
	}
}

@Preview
@Composable
fun TopBarPreviewDark() {
	TweetSocialTheme(darkTheme = true) {
		TopBar()
	}
}