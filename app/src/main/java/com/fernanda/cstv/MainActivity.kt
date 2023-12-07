package com.fernanda.cstv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.fernanda.navigation.graphs.AppNavigation
import com.fernanda.uikit.theme.MyTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.showSplashScreen }
        }

        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            viewModel.initNavigation(navHostController = navController)

            MyTheme {
                AppNavigation(navController = navController)
            }
        }
    }
}