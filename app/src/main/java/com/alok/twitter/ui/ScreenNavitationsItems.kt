package com.alok.twitter.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenNavitationsItems(var route: String, var icon: ImageVector, var title: String) {
	object Home : ScreenNavitationsItems("home", Icons.Filled.Home, "Home")
	object Search : ScreenNavitationsItems("search", Icons.Filled.Search, "Search")
	object Notification : ScreenNavitationsItems("notification", Icons.Filled.Notifications, "Notification")
	object Message : ScreenNavitationsItems("message", Icons.Filled.Message, "Message")
	object Profile : ScreenNavitationsItems("profile", Icons.Filled.VerifiedUser, "Profile")
}

val ScreenNavitationsItemsBottomBar: List<ScreenNavitationsItems> = listOf(
	ScreenNavitationsItems.Home,
	ScreenNavitationsItems.Search,
	ScreenNavitationsItems.Notification,
	ScreenNavitationsItems.Message,
)