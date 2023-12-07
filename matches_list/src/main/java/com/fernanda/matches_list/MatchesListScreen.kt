package com.fernanda.matches_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fernanda.uikit.R
import com.fernanda.uikit.components.AppScaffold
import com.fernanda.uikit.theme.Typography

@Composable
fun MatchesListScreen() {
    AppScaffold {
        Column {
            Text(
                text = stringResource(R.string.matches_list_title),
                style = Typography.titleLarge,
                color = Color.White,
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}