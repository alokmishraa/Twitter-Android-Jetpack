package com.alok.twitter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alok.twitter.ui.navigaton.Route
import com.alok.twitter.ui.navigaton.Routes

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = Route.Home.route) {
        Routes.forEach {
            composable(it.route, content = it.content)
        }
    }
}