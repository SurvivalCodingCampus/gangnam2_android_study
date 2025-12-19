package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {
    @Serializable
    data object Main : Route

    @Serializable
    data object Splash : Route

    @Serializable
    data object SignUp : Route

    @Serializable
    data object SignIn : Route

    @Serializable
    data object Home : Route

    @Serializable
    data object SavedRecipes : Route

    @Serializable
    data object Notifications : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data class RecipeItem(val recipe: Recipe) : Route

    @Serializable
    data object Search : Route


}