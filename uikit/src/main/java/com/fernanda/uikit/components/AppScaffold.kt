package com.fernanda.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fernanda.uikit.theme.Background
import com.fernanda.uikit.theme.MyTheme

@Composable
fun AppScaffold(content: @Composable () -> Unit) {
    MyTheme {
        Scaffold {
            Box(
                modifier = Modifier
                    .padding(it)
                    .background(color = Background)
                    .fillMaxSize()
            ) {
                content()
            }
        }
    }
}