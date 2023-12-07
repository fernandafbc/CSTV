package com.fernanda.match_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fernanda.uikit.components.AppScaffold

@Composable
fun MatchDetailsScreen(
    matchId: Long,
    matchLeague: String
) {
    AppScaffold {
        Column {
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = matchId.toString(), color = Color.White)
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = matchLeague, color = Color.White)
        }
    }
}