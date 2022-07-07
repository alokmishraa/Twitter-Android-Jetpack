package com.alok.twitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alok.twitter.ui.MainScreen
import com.alok.twitter.ui.theme.TwitterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TwitterTheme {
                Surface() {
                        MainScreen()
                }
            }
        }
    }
}
