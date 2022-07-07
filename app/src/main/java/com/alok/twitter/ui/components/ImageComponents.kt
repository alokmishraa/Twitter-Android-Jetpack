package com.alok.twitter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.math.MathUtils.clamp
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.alok.twitter.model.ImageHolder
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

// ------------------------------------------------
// Public Layouts
// ------------------------------------------------
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageLayout1(imageList: List<ImageHolder>) {
	SingleImage(
		imageList = imageList, 0, Modifier
			.fillMaxWidth()
			.aspectRatio(imageList[0].width / imageList[0].height.toFloat())
	)
}

@Composable
fun ImageLayout2(imageList: List<ImageHolder>) {
	Row(horizontalArrangement = Arrangement.Center) {
		SingleImage(
			imageList, 0,
			Modifier
				.fillMaxWidth(0.5f)
				.height(200.dp)
				.padding(end = 2.dp)
		)
		SingleImage(
			imageList, 1,
			Modifier
				.fillMaxWidth(1.0f)
				.height(200.dp)
				.padding(start = 2.dp)
		)
	}
}

@Composable
fun ImageLayout3(imageList: List<ImageHolder>) {
	Row(horizontalArrangement = Arrangement.Center) {
		SingleImage(
			imageList, 0,
			Modifier
				.fillMaxWidth(0.5f)
				.height(200.dp)
				.padding(end = 2.dp)
		)
		DoubleImageColumn(imageList, 1, 2, PaddingValues(start = 2.dp), 1.0f)
	}
}

@Composable
fun ImageLayout4(imageList: List<ImageHolder>) {
	Row(horizontalArrangement = Arrangement.Center) {
		DoubleImageColumn(imageList, 0, 1, PaddingValues(end = 2.dp), 0.5f)
		DoubleImageColumn(imageList, 2, 3, PaddingValues(start = 2.dp), 1.0f)
	}
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageLayoutN(imageList: List<ImageHolder>) {
	// Force Preload all Images
	//for (image in imageList) Image(painter = rememberImagePainter(image.getDataForPainter()), contentDescription = null, modifier = Modifier.size(1.dp))

	var size by remember { mutableStateOf(IntSize.Zero) }
	val context = LocalContext.current
	val imageLoader = LocalImageLoader.current

	if (size != IntSize.Zero) {
		for (image in imageList) {
			val request = ImageRequest.Builder(context)
				.data(image.getDataForPainter())
				.size(size.width, size.height)
				.build()
			imageLoader.enqueue(request)
		}
	}

	Box(
		Modifier
			.fillMaxSize()
			.background(Color(0.5f, 0.5f, 0.5f, .1f))
	) {
		val pagerState = rememberPagerState()
		HorizontalPager(
			count = imageList.size,
			state = pagerState,
			modifier = Modifier
				.fillMaxWidth()
				.onGloballyPositioned { coordinates ->
					size = coordinates.size
				},
		) { page ->
			SingleImage(
				imageList, page,
				Modifier
					.fillMaxWidth()
					.height(200.dp)
			)
		}
		HorizontalPagerIndicator(
			pagerState = pagerState,
			modifier = Modifier
				.padding(16.dp)
				.align(Alignment.BottomCenter)
		)
	}
}

// ------------------------------------------------
// Private Build Block
// ------------------------------------------------

@OptIn(ExperimentalPagerApi::class, ExperimentalComposeUiApi::class)
@Composable
private fun SingleImage(imageList: List<ImageHolder>, index: Int, modifier: Modifier) {
	var openFull by remember { mutableStateOf(false) }
	Image(
		painter = rememberImagePainter(
			data = imageList[index].getDataForPainter(),
			builder = {
				crossfade(true)
			},
		),
		"", //TODO
		modifier = modifier.then(
			Modifier
				.background(imageList[index].colorAverage)
				.clickable(onClick = {
					openFull = true
				})
		),
		contentScale = ContentScale.Crop
	)

	if (openFull) {
		Dialog(onDismissRequest = { openFull = false }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
			DraggableImage(imageList = imageList, index, draggedOutside = {
				openFull = false
			})
		}
	}
}

@ExperimentalPagerApi
@Composable
fun DraggableImage(imageList: List<ImageHolder>, current: Int, draggedOutside: () -> (Unit)) {
	val offsetState = remember { mutableStateOf(Offset(0f, 0f)) }
	val configuration = LocalConfiguration.current
	val offsetToQuit = configuration.screenHeightDp
	Surface(
		color = lerp(MaterialTheme.colors.surface, imageList[current].colorAverage, 0.2f)
			.copy(
				alpha = clamp(
					1.0f - (offsetState.value.y.absoluteValue / offsetToQuit),
					0.0f,
					1.0f
				)
			),
		modifier = Modifier
			.fillMaxSize()
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.pointerInput(Unit) {
					detectVerticalDragGestures(
						onVerticalDrag = { _, offset ->
							offsetState.value += Offset(offset.toDp().value, offset.toDp().value)
						},
						onDragEnd = {
							if (offsetState.value.y.absoluteValue > configuration.screenHeightDp / 4)
								draggedOutside()

							offsetState.value = Offset(0f, 0f)
						})
				}
		) {
			Column(
				Modifier
					.offset(0.dp, offsetState.value.y.dp)
					.align(Alignment.Center)
					.fillMaxSize()
			) {
				HorizontalPager(
					count = imageList.size,
					modifier = Modifier
						.fillMaxWidth()
				) { page ->
					Image(
						modifier = Modifier
							.fillMaxSize(),
						painter = rememberImagePainter(imageList[page].getDataForPainter()),
						contentDescription = "" //TODO
					)
				}
			}


		}
	}
}

@Composable
private fun DoubleImageColumn(imageList: List<ImageHolder>, indexTop: Int, indexBot: Int, paddingValues: PaddingValues, fraction: Float) {
	Column(
		modifier = Modifier
			.fillMaxWidth(fraction)
			.height(200.dp)
			.padding(paddingValues)
	) {
		SingleImage(
			imageList, indexTop,
			Modifier
				.fillMaxWidth(1.0f)
				.fillMaxHeight(0.5f)
				.padding(bottom = 2.dp)
		)
		SingleImage(
			imageList, indexBot,
			Modifier
				.fillMaxWidth(1.0f)
				.fillMaxHeight(1.0f)
				.padding(top = 2.dp)
		)
	}
}