package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.navigation

import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route

val bottomNavItemList = listOf(
    BottomNavItem(
        route = Route.Home,
        selectedIcon = R.drawable.ic_home_nav,
        unselectedIcon = R.drawable.ic_home_outline,
        label = "Home"
    ),
    BottomNavItem(
        route = Route.SavedRecipes,
        selectedIcon = R.drawable.ic_bookmark_nav,
        unselectedIcon = R.drawable.ic_bookmark_outline,
        label = "Saved"
    ),
    BottomNavItem(
        route = Route.Notifications,
        selectedIcon = R.drawable.ic_notification_bing_nav,
        unselectedIcon = R.drawable.ic_notification_bing_outline,
        label = "Notifications"
    ),
    BottomNavItem(
        route = Route.Profile,
        selectedIcon = R.drawable.ic_profile_nav,
        unselectedIcon = R.drawable.ic_profile_outline,
        label = "Profile"
    )
)
