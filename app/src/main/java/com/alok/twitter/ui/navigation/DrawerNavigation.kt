package com.alok.twitter.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.material.icons.outlined.MapsUgc
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.alok.twitter.data.DummyData
import com.alok.twitter.theme.ColorUtils.grayed
import com.alok.twitter.theme.TweetSocialTheme
import com.alok.twitter.utils.NotImplementedAlert

@Composable
fun Drawer() {
	Column(
		Modifier
			.fillMaxSize()
			.padding(vertical = 8.dp)
	)
	{
		var notImplementedAlertShow by remember { mutableStateOf(false) }
		if (notImplementedAlertShow) {
			NotImplementedAlert { notImplementedAlertShow = false }
		}
		UserPanel()
		Divider()
		Spacer(modifier = Modifier.height(8.dp))
		Divider()
		MenuPanel()
		Spacer(modifier = Modifier.height(8.dp))
		Divider()
		Spacer(modifier = Modifier.height(24.dp))
		Text(
			modifier = Modifier
				.padding(horizontal = 16.dp)
				.clickable(onClick = {
					notImplementedAlertShow = true
				}), text = "Parameters and confidentiality", fontSize = MaterialTheme.typography.body1.fontSize * 1.1
		)
		Spacer(modifier = Modifier.height(24.dp))
		Text(
			modifier = Modifier
				.padding(horizontal = 16.dp)
				.clickable(onClick = {
					notImplementedAlertShow = true
				}), text = "Information and Licences", fontSize = MaterialTheme.typography.body1.fontSize * 1.1
		)
	}
}

@Composable
fun MenuPanel() {
	var notImplementedAlertShow by remember { mutableStateOf(false) }
	if (notImplementedAlertShow) {
		NotImplementedAlert { notImplementedAlertShow = false }
	}
	Column(Modifier.padding(horizontal = 16.dp)) {
		MenuItem(Icons.Outlined.Person, "Profile") {
			notImplementedAlertShow = true
		}
		MenuItem(Icons.Outlined.ListAlt, "Lists") {
			notImplementedAlertShow = true
		}
		MenuItem(Icons.Outlined.MapsUgc, "Subjects") {
			notImplementedAlertShow = true
		}
		MenuItem(Icons.Outlined.BookmarkBorder, "Bookmarks") {
			notImplementedAlertShow = true
		}
	}
}

@Composable
fun MenuItem(icon: ImageVector, name: String, onClick: () -> (Unit)) {
	Row(
		Modifier
			.padding(vertical = 16.dp)
			.fillMaxWidth()
			.height(28.dp)
			.clickable(onClick = onClick)
	)
	{
		Icon(icon, "TODO", Modifier.size(28.dp))
		Spacer(modifier = Modifier.width(16.dp))
		Text(modifier = Modifier.align(CenterVertically), text = name, fontSize = MaterialTheme.typography.body1.fontSize * 1.1)
	}

}

@Composable
fun UserPanel() {
	val user = DummyData.currentUser
	Column(Modifier.padding(horizontal = 16.dp)) {
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
				.size(48.dp),
			contentScale = ContentScale.Crop
		)
		Spacer(modifier = Modifier.height(8.dp))
		Box(Modifier.fillMaxWidth()) {
			Column(Modifier.align(Alignment.CenterStart)) {
				Text(text = user.name, fontWeight = FontWeight.Bold)
				Text(text = "@${user.handle}", color = MaterialTheme.colors.onSurface.grayed())
			}
			IconButton(modifier = Modifier.align(Alignment.CenterEnd), onClick = {

			})
			{
				Icon(Icons.Filled.ExpandMore, "TODO")
			}
		}
		Spacer(modifier = Modifier.height(8.dp))
		Row(Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
			ProvideTextStyle(value = MaterialTheme.typography.body1.copy(fontSize = MaterialTheme.typography.body1.fontSize * 0.9)) {
				Text(
					buildAnnotatedString {
						withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
							append(user.following.toString())
						}
						withStyle(style = SpanStyle(color = MaterialTheme.colors.onSurface.grayed())) {
							append("  Following")
						}
					}
				)
				Text(
					buildAnnotatedString {
						withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
							append(user.follower.toString())
						}
						withStyle(style = SpanStyle(color = MaterialTheme.colors.onSurface.grayed())) {
							append("  Followers")
						}
					}
				)
			}
		}
		Spacer(modifier = Modifier.height(16.dp))
	}
}

@Preview
@Composable
fun DrawerPreview() {
	TweetSocialTheme(darkTheme = false) {
		Drawer()
	}
}

@Preview
@Composable
fun DrawerPreviewDark() {
	TweetSocialTheme(darkTheme = true) {
		Drawer()
	}
}