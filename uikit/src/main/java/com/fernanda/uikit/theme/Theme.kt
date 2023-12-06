package com.fernanda.uikit.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val colors = lightColorScheme(
    primary = Color.White,
    secondary = SteelGray,
    tertiary = ImperialRed,
    background = EerieBlack,
    surface = StormGray

)

@Composable
fun MyTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = colors
    val view = LocalView.current
    val activity = view.context as Activity
    if (!view.isInEditMode) {
        SideEffect {
            activity.window.statusBarColor = colorScheme.background.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}