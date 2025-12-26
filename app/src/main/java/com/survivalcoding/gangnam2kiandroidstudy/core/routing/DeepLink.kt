package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import android.net.Uri

sealed class DeepLink {
    object SavedRecipes : DeepLink()
    data class RecipeDetail(val id: Int) : DeepLink()

    companion object {
        fun fromUri(uri: Uri): DeepLink? {
            if (uri.scheme != "myapp" || uri.host != "recipes") return null

            val segments = uri.pathSegments
            return when {
                segments.contains("saved") -> SavedRecipes
                segments.firstOrNull() == "detail" -> {
                    val id = segments.getOrNull(1)?.toIntOrNull()
                    if (id != null) RecipeDetail(id) else null
                }
                else -> null
            }
        }
    }
}