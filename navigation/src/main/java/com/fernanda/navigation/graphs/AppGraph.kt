package com.fernanda.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fernanda.matches_list.MatchesListScreen
import com.fernanda.navigation.core.Destination
import com.fernanda.navigation.routes.Routes

internal fun NavGraphBuilder.addMatchNavGraph() {
    navigation(
        route = Destination.MatchesList.route,
        startDestination = Routes.MatchesList.createRoute()
    ) {
        addMatchesList()
    }
}

private fun NavGraphBuilder.addMatchesList() {
    composable(
        route = Routes.MatchesList.createRoute(),
        content = {
            MatchesListScreen()
        }
    )
}