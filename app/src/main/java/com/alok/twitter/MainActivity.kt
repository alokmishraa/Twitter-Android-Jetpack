package com.alok.twitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alok.twitter.theme.TweetSocialTheme
import com.alok.twitter.ui.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		installSplashScreen()
		setContent {
			TweetSocialTheme {
				MainScreen()
			}
		}
	}
}