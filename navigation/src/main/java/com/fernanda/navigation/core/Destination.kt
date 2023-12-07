package com.fernanda.navigation.core

sealed class Destination(val route: String) {
    object MatchesList : Destination(route = "MatchesList")
    object MatchDetails : Destination(route = "MatchDetails")
}