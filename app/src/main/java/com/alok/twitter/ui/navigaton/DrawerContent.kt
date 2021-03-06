package com.alok.twitter.ui.navigaton

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.alok.twitter.R
import com.alok.twitter.data.User
import kotlinx.coroutines.launch


val user = User()

data class Catalog(val rsKey: Int, val title: String)

val bottomNavigations = listOf(
    Route.Profile,
    Route.Lists,
    Route.Topics,
    Route.Bookmarks,
    Route.Moments,
    Route.Settings
)

@Composable
fun DrawerContent(navController: NavHostController, scaffoldState: ScaffoldState) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        color = MaterialTheme.colors.background
    ) {
        Column() {
            ProfileContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
            )
            Divider(
                modifier = Modifier.padding(top = 20.dp),
                color = Color.LightGray,
                thickness = 0.25.dp
            )
            ListContent(
                navController,
                scaffoldState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
            )
            BottomContent()
        }
    }
}

@Composable
fun ProfileContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        AsyncImage(
            model = user.profileUrl,
            contentDescription = "Profile Image",
            modifier = Modifier
                .width(55.dp)
                .clip(shape = CircleShape)
        )
        Text(
            user.displayName,
            modifier = Modifier.padding(top = 7.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text("@" + user.userName, color = MaterialTheme.colors.secondary)
        Row(modifier = Modifier.padding(top = 15.dp)) {
            Row() {
                Text(user.following.toString(), fontWeight = FontWeight.Bold)
                Text(" Following", color = MaterialTheme.colors.secondary)
            }
            Row(modifier = Modifier.padding(start = 10.dp)) {
                Text(user.follower.toString(), fontWeight = FontWeight.Bold)
                Text(" Followers", color = MaterialTheme.colors.secondary)
            }
        }
    }
}

@Composable
fun ListContent(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn() {
            items(items = bottomNavigations) {
                NavigationRow(navController, scaffoldState, it)
            }
            item {
                Divider(
                    modifier = Modifier.padding(vertical = 15.dp),
                    color = Color.LightGray,
                    thickness = 0.25.dp
                )
                Column(modifier = modifier.padding(horizontal = 25.dp)) {
                    Text(
                        text = "Privacy Policy",
                        fontSize = 18.sp,
                        fontWeight = W500
                    )
                    Text(
                        text = "Support Center",
                        modifier = Modifier.padding(vertical = 15.dp),
                        fontSize = 18.sp,
                        fontWeight = W500
                    )
                }
            }
        }

    }
}

@Composable
fun NavigationRow(navController: NavHostController, scaffoldState: ScaffoldState, route: Route) {
    val coroutineScope = rememberCoroutineScope()
    val currentRoute = currentRoute(navController)
    Row(
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 25.dp)
            .clickable(onClick = {
                if (currentRoute != route.route) {
                    navController.navigate(route.route)
                }
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(route.icon), null,
            modifier = Modifier.size(22.dp),
            tint = MaterialTheme.colors.secondary
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = route.route,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun BottomContent() {
    Column(modifier = Modifier) {
        Divider(
            modifier = Modifier,
            color = Color.LightGray,
            thickness = 0.25.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_theme),
                    null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.primary
                )
            }
            Box(modifier = Modifier.weight(1F))
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_qrcode),
                    null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}