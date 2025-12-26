package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import android.net.Uri

object DeepLinkParser {
    fun parse(uri: Uri): Route? {
        if (uri.scheme != "myapp" || uri.host != "recipes") return null

        val pathSegments = uri.pathSegments
        return when (pathSegments.size) {
            1 if pathSegments[0] == "saved" -> {
                Route.SavedRecipes
            }
            2 if pathSegments[0] == "detail" -> {
                pathSegments[1].toLongOrNull()?.let { recipeId ->
                    Route.RecipeDetails(recipeId)
                }
            }
            else -> null
        }
    }
}
