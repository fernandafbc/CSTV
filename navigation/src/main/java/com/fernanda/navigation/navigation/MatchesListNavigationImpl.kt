package com.fernanda.navigation.navigation

import com.fernanda.matches_list.MatchesListNavigation
import com.fernanda.navigation.core.NavigationManager
import com.fernanda.navigation.routes.Routes

class MatchesListNavigationImpl(
    private val navigationManager: NavigationManager
) : MatchesListNavigation {
    override fun goToMatchDetails(matchId: Long, matchLeague: String, matchDate: String) {
        navigationManager.navigate(
            route = Routes.MatchDetails.putArgs(
                args = listOf(matchId.toString(), matchLeague, matchDate)
            )
        )
    }
}