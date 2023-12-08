package com.fernanda.matches_list

interface MatchesListNavigation {
    fun goToMatchDetails(matchId: Long, matchLeague: String, matchDate: String)
}