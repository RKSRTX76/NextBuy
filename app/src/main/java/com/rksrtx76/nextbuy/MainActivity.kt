package com.rksrtx76.nextbuy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rksrtx76.nextbuy.presentation.navigation.AppNavigation
import com.rksrtx76.nextbuy.ui.theme.NextBuyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        setContent {
            NextBuyTheme {
                AppNavigation()
            }
        }
    }
}
