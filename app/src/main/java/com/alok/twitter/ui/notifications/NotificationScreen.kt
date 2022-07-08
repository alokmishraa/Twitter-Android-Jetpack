package com.alok.twitter.ui.notifications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NotificationScreen() {
    val notificationVM: NotificationsViewModel = hiltViewModel()
    Surface(
        modifier = Modifier.padding(),
    ) {
        val selectedTab = remember {
            mutableStateOf(NotificationsTab.All)
        }
        val tabTitles = NotificationsTab.values().map { it.title }
        Column {
            NotificationsTab(selectedTab, tabTitles)
            NotificationsResults(notificationVM)
        }
    }
}


@Composable
fun NotificationsTab(selectedTab: MutableState<NotificationsTab>, tabTitles: List<String>) {
    TabRow(
        selectedTabIndex = selectedTab.value.ordinal,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onBackground
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = isSelected(index, selectedTab),
                onClick = {
                    selectedTab.value = NotificationsTab.values().first { it.title == title }
                },
                text = {
                    Text(
                        title,

                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = textColor(isSelected(index, selectedTab))
                    )
                })
        }
    }
}

@Composable
private fun isSelected(
    index: Int,
    selectedTab: MutableState<NotificationsTab>
) = index == selectedTab.value.ordinal

@Composable
fun textColor(selected: Boolean): Color {
    return if (selected) {
        MaterialTheme.colors.onBackground
    } else {
        MaterialTheme.colors.onSecondary
    }
}