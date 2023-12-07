package com.fernanda.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fernanda.navigation.core.Destination

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Destination.MatchesList.route
    ) {
        addMatchNavGraph()
    }
}