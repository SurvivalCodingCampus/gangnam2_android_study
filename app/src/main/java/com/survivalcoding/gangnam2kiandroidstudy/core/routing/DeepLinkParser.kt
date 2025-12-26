package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import android.net.Uri

object DeepLinkParser {

    fun parse(uri: Uri): Route? {
        return when (uri.scheme) {
            "myapp" -> parseCustomScheme(uri)
            "http", "https" -> parseAppLink(uri)
            else -> null
        }
    }

    private fun parseCustomScheme(uri: Uri): Route? {
        if (uri.host != "recipes") return null

        val pathSegments = uri.pathSegments
        return when (pathSegments.size) {
            1 if pathSegments[0] == "saved" -> Route.SavedRecipes
            2 if pathSegments[0] == "detail" -> {
                pathSegments[1].toLongOrNull()?.let { Route.RecipeDetails(it) }
            }
            else -> null
        }
    }

    private fun parseAppLink(uri: Uri): Route? {
        val pathSegments = uri.pathSegments

        if (pathSegments.size < 2 || pathSegments[0] != "recipes") return null

        return when (pathSegments[1]) {
            "saved" -> Route.SavedRecipes
            "detail" -> pathSegments.getOrNull(2)?.toLongOrNull()?.let { Route.RecipeDetails(it) }
            else -> null
        }
    }
}
