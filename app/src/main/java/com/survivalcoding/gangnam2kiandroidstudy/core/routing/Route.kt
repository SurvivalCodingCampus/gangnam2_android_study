package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {

    @Serializable
    data object SingIn : Route
}