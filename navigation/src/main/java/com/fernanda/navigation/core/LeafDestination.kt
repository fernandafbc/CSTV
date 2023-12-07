package com.fernanda.navigation.core

open class LeafDestination(
    val root: Destination,
    private val route: String
) {
    fun createRoute() = "${root.route}/$route"

    fun createRouteWithArgs(args: List<String>): String {
        var route = "${root.route}/$route"
        args.forEach {
            route = route.plus("/{$it}")
        }
        return route
    }

    fun putArgs(args: List<String>): String {
        var route = "${root.route}/$route"
        args.forEach {
            route = route.plus("/$it")
        }
        return route
    }
}