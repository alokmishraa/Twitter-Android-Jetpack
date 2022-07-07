package com.alok.twitter.ui

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alok.twitter.ui.navigation.AppNavigation
import com.alok.twitter.ui.navigation.AppTopBar
import com.alok.twitter.ui.navigaton.AppBottomBar
import com.alok.twitter.ui.navigaton.DrawerContent
import com.alok.twitter.ui.navigaton.Route
import com.alok.twitter.viewmodel.MainViewModel
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    viewModel.navHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope() // For open() and close() of the drawer

    val navBackStackEntry by viewModel.navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .statusBarsPadding(),
        bottomBar = {
            AppBottomBar(viewModel.navHostController)
        },
        topBar = {
            AppTopBar(viewModel.navHostController, scaffoldState)
        },
        drawerContent = {
            DrawerContent(viewModel.navHostController, scaffoldState)
        },
        floatingActionButton = {
            FloatingButton(currentRoute)
        },
        scaffoldState = scaffoldState
    ) {
        if (scaffoldState.drawerState.isOpen) { // Handle back to close Drawer
            BackHandler {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
        Column(modifier = Modifier.padding(bottom = 58.dp)) {
            AppNavigation(viewModel.navHostController)
        }
    }
}

@Composable
fun FloatingButton(currentRoute: String) {
    if (currentRoute == Route.Home.route) {
        NewTweetButton()
    }
    if (currentRoute == Route.Message.route) {
        NewMessageButton()
    }
}