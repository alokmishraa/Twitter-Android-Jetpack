package com.alok.twitter.ui.navigaton

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.alok.twitter.R
import com.alok.twitter.ui.screen.*


sealed class Route(
    val route: String,
    val icon: Int,
    val content: @Composable (backStackEntry: NavBackStackEntry) -> Unit = {}
) {
    object Home : Route("Home", R.drawable.ic_home, { HomeScreen() })
    object Search : Route("Search", R.drawable.ic_search, { SearchScreen() })
    object Notification : Route(
        "Notification",
        R.drawable.ic_notifications,
        { _ -> NotificationScreen() })

    object Message : Route("Message", R.drawable.ic_dm, { MessageScreen() })
    object Profile : Route("Profile", R.drawable.ic_profile, { ProfileScreen() })
    object Lists : Route("Lists", R.drawable.ic_lists, { ProfileScreen() })
    object Topics : Route("Topics", R.drawable.ic_topics, { ProfileScreen() })
    object Bookmarks :
        Route("Bookmarks", R.drawable.ic_bookmarks, { ProfileScreen() })

    object Moments : Route("Moments", R.drawable.ic_moments, { ProfileScreen() })
}

val Routes = listOf(
    // bottom
    Route.Home,
    Route.Search,
    Route.Notification,
    Route.Message,

    //drawer
    Route.Profile,
    Route.Lists,
    Route.Topics,
    Route.Bookmarks,
    Route.Moments
)

@Composable
public fun currentRoute(navController: NavHostController): String? {
    return navController.currentBackStackEntry?.destination?.route
}