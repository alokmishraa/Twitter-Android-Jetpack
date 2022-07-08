package com.alok.twitter.ui.feeds

import android.text.format.DateUtils
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.alok.twitter.R
import com.alok.twitter.ui.common.ComposeTwitterFeedText
import com.alok.twitter.ui.feeds.data.Tweet

@Composable
fun ComposeTweet(
    tweet: Tweet,
    onUrlRecognized: (Tweet, String) -> Unit,
    onClickTweet: (Tweet) -> Unit,
    hashTagNavigator: (String) -> Unit
) {
    Surface(modifier = Modifier.clickable {
        onClickTweet.invoke(tweet)
    }) {
        Column {
            Row(modifier = Modifier.padding(12.dp)) {
                RoundedUserImage(url = tweet.tUImage)
                Spacer(modifier = Modifier.width(14.dp))
                ComposeTweetColumn(
                    tweet,
                    onUrlRecognized,
                    hashTagNavigator = hashTagNavigator,
                    onClickTweet
                )
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
    }
}

@Composable
private fun ComposeTweetColumn(
    tweet: Tweet,
    onUrlRecognized: (Tweet, String) -> Unit,
    hashTagNavigator: (String) -> Unit,
    onClickTweet: (Tweet) -> Unit
) {
    Column {
        ComposeNameHandlerOverflow(tweet.tUName, tweet.tUHandler, true)
        ComposeTime(tweet.tUTime)
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTwitterFeedText(
            tweet.tUText,
            urlRecognizer = { url ->
                if (tweet.metadata == null) {
                    onUrlRecognized.invoke(tweet, url)
                }
            },
            hashTagNavigator = hashTagNavigator,
            textClick = {
                onClickTweet.invoke(tweet)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTweetMetadata(tweet)
        ComposeFooter(tweet)
    }
}

@Composable
fun ComposeFooter(tweet: Tweet) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_reply),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = tweet.tCommentCount.toString(),
                modifier = Modifier.padding(start = 4.dp),
                fontSize = 14.sp
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_retweet),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = tweet.tRTCount.toString(), modifier = Modifier.padding(start = 4.dp),
                fontSize = 14.sp
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_like), contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = tweet.tLikeCount.toString(), modifier = Modifier.padding(start = 4.dp),
                fontSize = 14.sp
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_share),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

    }
}


@Composable
fun ComposeTweetMetadata(tweet: Tweet) {
    val uriHandler = LocalUriHandler.current
    if (tweet.metadata != null && tweet.metadata?.title != null) {
        val tweetUrlMeta = tweet.metadata!!
        Surface(
            shape = RoundedCornerShape(12.dp),
            modifier = surfaceModifier(tweet, uriHandler)
        ) {
            ConstraintLayout {
                val (image, footer) = createRefs()
                AsyncImage(
                    model = tweetUrlMeta.image ?: tweet.tUImage,
                    contentDescription = null, modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .height(180.dp)
                        .fillMaxWidth(), contentScale = ContentScale.Crop
                )
                ComposeMetadataFooter(tweetUrlMeta.title!!,
                    tweetUrlMeta.desc!!,
                    modifier = Modifier
                        .constrainAs(footer) {
                            bottom.linkTo(parent.bottom)
                        }
                        .fillMaxWidth())
            }
        }
    }
}

@Composable
private fun surfaceModifier(
    tweet: Tweet,
    uriHandler: UriHandler
) = Modifier
    .clickable {
        tweet.metadata?.url?.let { uriHandler.openUri(it) }
    }
    .border(
        2.dp,
        Color.Gray,
        RoundedCornerShape(12.dp)
    )

@Composable
fun ComposeMetadataFooter(title: String, desc: String, modifier: Modifier) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        Column {
            Text(
                title,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                desc,
                fontSize = 10.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun ComposeTime(time: Long, color: Color? = null) {
    Text(
        DateUtils.getRelativeTimeSpanString(
            time,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp,
        color = color ?: MaterialTheme.colors.primary
    )
}

@Composable
fun ComposeNameHandlerOverflow(name: String, tUHandler: String, showOverflow: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.9f)) {
            Text(
                name, fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                tUHandler,
                color = MaterialTheme.colors.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (showOverflow) {
            Icon(
                painterResource(id = R.drawable.ic_vector_overflow),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun RoundedUserImage(
    url: String, modifier: Modifier = Modifier
        .requiredSize(60.dp)
        .padding(2.dp)
) {
    Surface(
        shape = CircleShape,
        modifier = modifier,
        contentColor = MaterialTheme.colors.primary
    ) {
        AsyncImage(
            url,
            contentDescription = null
        )
    }
}