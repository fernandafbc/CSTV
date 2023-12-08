package com.fernanda.navigation.navigation

import com.fernanda.match_details.MatchDetailsNavigation
import com.fernanda.navigation.core.NavigationManager

class MatchDetailsNavigationImpl(
    private val navigationManager: NavigationManager
) : MatchDetailsNavigation {
    override fun popBackStack() {
        navigationManager.popStackBack()
    }
}