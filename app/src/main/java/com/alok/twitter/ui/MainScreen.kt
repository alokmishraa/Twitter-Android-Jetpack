package com.alok.twitter.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import com.alok.twitter.MainViewModel
import com.alok.twitter.theme.ColorUtils.grayed
import com.alok.twitter.ui.navigation.BottomBar
import com.alok.twitter.ui.navigation.Drawer
import com.alok.twitter.ui.navigation.TopBar
import com.alok.twitter.ui.navigation.TopBarExtra
import com.alok.twitter.ui.screens.HomeScreen
import com.alok.twitter.ui.screens.MessageScreen
import com.alok.twitter.ui.screens.NotificationScreen
import com.alok.twitter.ui.screens.SearchScreen
import com.alok.twitter.utils.NotImplementedAlert
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun MainScreen(
	viewModel: MainViewModel = viewModel()
) {
	viewModel.navHostController = rememberNavController()
	val postList by viewModel.postList.observeAsState()
	val userList by viewModel.userList.observeAsState()

	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope() // For open() and close() of the drawer

	var notImplementedAlertShow by remember { mutableStateOf(false) }
	if (notImplementedAlertShow) {
		NotImplementedAlert { notImplementedAlertShow = false }
	}

	val navBackStackEntry by viewModel.navHostController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route ?: ""

	Scaffold(
		scaffoldState = scaffoldState,
		topBar = {
			when (currentRoute) {
				ScreenNavitationsItems.Home.route ->
					TopBar(
						onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
						onActionIconPressed = { notImplementedAlertShow = true }
					)
				ScreenNavitationsItems.Search.route ->
					TopBarExtra(
						onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
						onActionIconPressed = { notImplementedAlertShow = true },
						title = "Search"
					)
				ScreenNavitationsItems.Notification.route ->
					TopBarExtra(
						onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
						onActionIconPressed = { notImplementedAlertShow = true },
						title = "Notification"
					)
				ScreenNavitationsItems.Message.route ->
					TopBarExtra(
						onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
						onActionIconPressed = { notImplementedAlertShow = true },
						title = "Message"
					)
			}
		},
		drawerContent = if (currentRoute in ScreenNavitationsItemsBottomBar.map { it.route }) {
			{ Drawer() }
		} else null,
		drawerScrimColor = Color.Black.copy(alpha = 0.5f),
		drawerElevation = 0.dp,
		floatingActionButton = {
			if (currentRoute == ScreenNavitationsItems.Home.route)
				FloatingActionButton(
					onClick = { notImplementedAlertShow = true },
					backgroundColor = MaterialTheme.colors.primary,
					contentColor = MaterialTheme.colors.onPrimary
				) {
					Icon(Icons.Filled.Add, "")
				}
		},
		bottomBar = {
			if (currentRoute in ScreenNavitationsItemsBottomBar.map { it.route }) // Only show if current route is one of the botbar
				BottomBar(viewModel.navHostController)
		}
	) { paddingValues: PaddingValues ->

		if (scaffoldState.drawerState.isOpen) { // Handle back to close Drawer
			BackHandler {
				scope.launch {
					scaffoldState.drawerState.close()
				}
			}
		}

		Surface {
			NavHost(viewModel.navHostController, startDestination = ScreenNavitationsItems.Home.route) {
				composable(ScreenNavitationsItems.Home.route) {
					HomeScreen(viewModel = viewModel, padding = paddingValues, postList = postList?.data ?: listOf(), userList = userList?.data ?: listOf())
				}
				composable(ScreenNavitationsItems.Search.route) {
					SearchScreen()
				}
				composable(ScreenNavitationsItems.Notification.route) {
					NotificationScreen()
				}
				composable(ScreenNavitationsItems.Message.route) {
					MessageScreen()
				}
				composable("${ScreenNavitationsItems.Profile.route}/{userId}", arguments = listOf(navArgument("userId") { type = NavType.StringType })) {
					Column(Modifier.fillMaxSize()) {
						val user = userList?.data?.find { it.uid == UUID.fromString(navBackStackEntry?.arguments?.getString("userId")) }
						user?.let { userNonNull ->
//							Text("Name:	 ${user.name}")
//							Text("Handle:	 ${user.handle}")
//							Text("Handle:	 ${user.}")
//
							Image(
								painter = rememberImagePainter(
									data = userNonNull.avatar.getDataForPainter(),
									builder = {
										crossfade(true)
									}
								),
								"",
								modifier = Modifier
									.size(50.dp)
									.clip(shape = CircleShape)
									.clickable(onClick = { viewModel.navigateTo("${ScreenNavitationsItems.Profile.route}/${userNonNull.uid}") }),
								contentScale = ContentScale.Crop
							)

							Row(verticalAlignment = Alignment.CenterVertically) {
								Text(
									text = userNonNull.name,
									overflow = TextOverflow.Ellipsis,
									style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
								)
								if (userNonNull.verified) {
									Spacer(modifier = Modifier.width(5.dp))
									Icon(
										Icons.Filled.VerifiedUser,
										"",
										tint = MaterialTheme.colors.primary,
										modifier = Modifier.size(16.dp)
									)
								}
								Spacer(modifier = Modifier.size(5.dp))
								Text(text = "@${userNonNull.handle}", color = MaterialTheme.colors.onSurface.grayed(), maxLines = 1)
							}
						}
					}
				}
			}

		}
	}
}