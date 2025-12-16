package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.navigation.BottomNavBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.navigation.bottomNavItemList
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notifications.NotificationsScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile.ProfileScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.SavedRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun MainRoot() {
    val mainBackStack = rememberNavBackStack(Route.Home)
    val currentKey = mainBackStack.lastOrNull() ?: Route.Home

    val showBottomBar = currentKey in listOf(
        Route.Home,
        Route.SavedRecipes,
        Route.Notifications,
        Route.Profile
    )

    Scaffold(
        containerColor = AppColors.white,
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    items = bottomNavItemList,
                    currentRoute = currentKey,
                    onItemClick = { route ->
                        if (route != currentKey) {
                            mainBackStack.clear()
                            mainBackStack.add(route)
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = mainBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {

                entry<Route.Home> {
                    HomeRoot(
                        onNavigateToSearch = {
                            mainBackStack.add(Route.SearchRecipes)
                        }
                    )
                }

                entry<Route.SavedRecipes> {
                    SavedRecipesRoot()
                }

                entry<Route.Profile> {
                    ProfileScreen()
                }

                entry<Route.Notifications> {
                    NotificationsScreen()
                }

                entry<Route.SearchRecipes> {
                    SearchRecipesRoot(
                        onBack = {
                            if (mainBackStack.size > 1) {
                                mainBackStack.removeAt(mainBackStack.lastIndex)
                            }
                        }
                    )
                }
            }
        )
    }
}
