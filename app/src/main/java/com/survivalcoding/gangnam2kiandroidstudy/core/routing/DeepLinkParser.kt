package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import android.net.Uri
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route

object DeepLinkParser {
    fun parse(uri: Uri?): Route? {
        if (uri == null) return null

        // Handle myapp scheme
        if (uri.scheme == "myapp" && uri.host == "recipes") {
            return when {
                uri.path == "/saved" -> Route.Main(initialTab = Route.SavedRecipes)
                uri.path?.startsWith("/detail/") == true -> {
                    val id = uri.lastPathSegment?.toIntOrNull() ?: 0
                    Route.RecipeDetail(id)
                }

                else -> null
            }
        }

        // Handle https scheme
        if (uri.scheme == "https") {
            val segments = uri.pathSegments
            if (segments.size >= 2 && segments[0] == "recipes") {
                return when (segments[1]) {
                    "saved" -> Route.Main(initialTab = Route.SavedRecipes)
                    else -> {
                        val id = segments[1].toIntOrNull()
                        if (id != null) Route.RecipeDetail(id) else null
                    }
                }
            }
        }

        return null
    }
}
