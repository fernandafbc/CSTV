package com.fernanda.navigation.routes

import com.fernanda.navigation.core.Destination
import com.fernanda.navigation.core.LeafDestination

object Routes {
    object MatchesList : LeafDestination(root = Destination.MatchesList, route = "main")
}