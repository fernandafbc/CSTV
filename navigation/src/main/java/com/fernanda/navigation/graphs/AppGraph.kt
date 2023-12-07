package com.fernanda.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fernanda.match_details.MatchDetailsScreen
import com.fernanda.matches_list.MatchesListScreen
import com.fernanda.navigation.core.Destination
import com.fernanda.navigation.core.NavigationConstants.match_id
import com.fernanda.navigation.core.NavigationConstants.match_league
import com.fernanda.navigation.routes.Routes

internal fun NavGraphBuilder.addMatchNavGraph() {
    navigation(
        route = Destination.MatchesList.route,
        startDestination = Routes.MatchesList.createRoute()
    ) {
        addMatchesList()
        addMatchDetails()
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

private fun NavGraphBuilder.addMatchDetails() {
    composable(
        route = Routes.MatchDetails.createRouteWithArgs(
            listOf(match_id, match_league)
        ),
        arguments = listOf(
            navArgument(match_id) { type = NavType.StringType },
            navArgument(match_league) { type = NavType.StringType }
        ),
        content = {
            val id = it.arguments?.getString(match_id).orEmpty()
            val league = it.arguments?.getString(match_league).orEmpty()
            MatchDetailsScreen(matchId = id, matchLeague = league)
        }
    )
}