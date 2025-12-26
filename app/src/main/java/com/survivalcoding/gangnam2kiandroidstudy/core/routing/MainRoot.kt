package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.navigation.BottomNavBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.navigation.bottomNavItemList
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notifications.NotificationsScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile.ProfileScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.SavedRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun MainRoot(
    deepLinkUri: String? = null,
) {
    // 1. 초기 스택 생성
    val mainBackStack = rememberNavBackStack(Route.Home)

    // 2. deepLinkUri가 들어오면 즉시 스택 조작
    LaunchedEffect(deepLinkUri) {
        if (deepLinkUri != null) {
            val uri = deepLinkUri.toUri()
            val pathSegments = uri.pathSegments

            when {
                // myapp://recipes/saved
                pathSegments.contains("saved") -> {
                    mainBackStack.clear()
                    mainBackStack.add(Route.SavedRecipes)
                }
                // myapp://recipes/detail/1
                pathSegments.contains("detail") -> {
                    val recipeId = pathSegments.getOrNull(1)?.toIntOrNull()
                    if (recipeId != null) {
                        mainBackStack.clear()
                        mainBackStack.add(Route.Home) // 뒤로가기 시 홈으로 가도록 설정
                        mainBackStack.add(Route.RecipeDetail(recipeId))
                    }
                }
            }
        }
    }

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
                        },
                        onNavigateToRecipeDetail = { recipeId ->
                            mainBackStack.add(Route.RecipeDetail(recipeId))
                        }
                    )
                }

                entry<Route.SavedRecipes> {
                    SavedRecipesRoot(
                        onNavigateToRecipeDetail = { recipeId ->
                            mainBackStack.add(Route.RecipeDetail(recipeId))
                        }
                    )
                }

                entry<Route.Profile> {
                    ProfileScreen()
                }

                entry<Route.Notifications> {
                    NotificationsScreen()
                }

                entry<Route.SearchRecipes> {
                    SearchRecipesRoot(
                        onNavigateToRecipeDetail = { recipeId ->
                            mainBackStack.add(Route.RecipeDetail(recipeId))
                        },
                        onBack = {
                            if (mainBackStack.size > 1) {
                                mainBackStack.removeAt(mainBackStack.lastIndex)
                            }
                        }
                    )
                }
                entry<Route.RecipeDetail> { route ->
                    IngredientRoot(
                        recipeId = route.recipeId,
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
