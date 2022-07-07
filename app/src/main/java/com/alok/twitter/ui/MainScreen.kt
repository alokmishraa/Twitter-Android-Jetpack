package com.alok.twitter.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alok.twitter.ui.navigation.AppNavigation
import com.alok.twitter.ui.navigation.AppTopBar
import com.alok.twitter.ui.navigaton.AppBottomBar
import com.alok.twitter.ui.navigaton.DrawerContent
import com.alok.twitter.ui.navigaton.Route
import com.alok.twitter.ui.navigaton.currentRoute
import com.google.accompanist.insets.*
import com.google.accompanist.insets.statusBarsHeight

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .statusBarsPadding(),
        bottomBar = {
            AppBottomBar(navController)
        },
        topBar = {
            AppTopBar(navController, scaffoldState)
        },
        drawerContent = {
            DrawerContent(navController, scaffoldState)
        },
        floatingActionButton = {
            FloatingButton(navController)
        },
        scaffoldState = scaffoldState
    ) {
        // ovoid bottom bar overlay content
        Column(modifier = Modifier.padding(bottom = 58.dp)) {
            AppNavigation(navController)
        }
    }
}

@Composable
fun FloatingButton(navController: NavHostController) {
    if (currentRoute(navController) == Route.Home.route) {
        NewTweetButton()
    }
    if (currentRoute(navController) == Route.Message.route) {
        NewMessageButton()
    }
}