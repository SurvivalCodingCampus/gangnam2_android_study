package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.navigation

import androidx.annotation.DrawableRes
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route

data class BottomNavItem(
    val route: Route,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val label: String,
)