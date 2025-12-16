package com.survivalcoding.gangnam2kiandroidstudy.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun MainScreen(
    body: @Composable (modifier: Modifier) -> Unit,
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
) {
    val currentRoute = backStack.lastOrNull()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = AppColors.white,
                contentColor = AppColors.gray4
            ) {
                NavigationBarItem(
                    selected = currentRoute is Route.Home,
                    onClick = {
                        backStack.clear()
                        backStack.add(Route.Home)
                    },
                    icon = {
                        Icon(
                            painter = if (currentRoute is Route.Home)
                                painterResource(R.drawable.bottom_home_primary)
                            else
                                painterResource(R.drawable.bottom_home_gray),
                            contentDescription = "Home",
                            tint = Color.Unspecified
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = currentRoute is Route.SavedRecipes,
                    onClick = {
                        backStack.clear()
                        backStack.add(Route.SavedRecipes)
                    },
                    icon = {
                        Icon(
                            painter = if (currentRoute is Route.SavedRecipes)
                                painterResource(R.drawable.bottom_saved_primary)
                            else
                                painterResource(R.drawable.bottom_saved_gray),
                            contentDescription = "Saved Recipes",
                            tint = Color.Unspecified
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = currentRoute is Route.Notifications,
                    onClick = {
                        backStack.clear()
                        backStack.add(Route.Notifications)
                    },
                    icon = {
                        Icon(
                            painter = if (currentRoute is Route.Notifications)
                                painterResource(R.drawable.bottom_notification_primary)
                            else
                                painterResource(R.drawable.bottom_notification_gray),
                            contentDescription = "Notifications",
                            tint = Color.Unspecified
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = currentRoute is Route.Profile,
                    onClick = {
                        backStack.clear()
                        backStack.add(Route.Profile)
                    },
                    icon = {
                        Icon(
                            painter = if (currentRoute is Route.Profile)
                                painterResource(R.drawable.bottom_profile_primary)
                            else
                                painterResource(R.drawable.bottom_profile_gray),
                            contentDescription = "Profile",
                            tint = Color.Unspecified
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    ) {
        body(modifier.padding(it))
    }

}