package com.alok.twitter.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alok.twitter.R
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun SwipeProgressIndicator(
    swipeRefreshState: SwipeRefreshState,
    refreshTriggerDistance: Dp,
) {
    if (swipeRefreshState.isSwipeInProgress) {
        val trigger = with(LocalDensity.current) { refreshTriggerDistance.toPx() }
        val progress = (swipeRefreshState.indicatorOffset / trigger).coerceIn(0f, 2f)
        val progressPadding = (swipeRefreshState.indicatorOffset / trigger) * 32f
        val angle: Float = progress * 360F
        if (progressPadding > 0) {
            Surface(
                shape = CircleShape,
                modifier = Modifier.padding(top = progressPadding.dp),
                contentColor = MaterialTheme.colors.background
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(angle)
                        .size(36.dp)
                )
            }
        }

    }
    if (swipeRefreshState.isRefreshing) {
        Surface(
            shape = CircleShape,
            modifier = Modifier.padding(top = 32.dp),
            contentColor = MaterialTheme.colors.background
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(4.dp)
                    .size(36.dp), color = MaterialTheme.colors.primary
            )
        }
    }

}