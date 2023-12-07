package com.fernanda.navigation.core

open class LeafDestination(
    val root: Destination,
    private val route: String
) {
    fun createRoute() = "${root.route}/$route"
}