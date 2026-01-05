package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe.SavedRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in.SignInRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up.SignUpRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashRoot
import kotlinx.coroutines.flow.Flow

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    deepLinkFlow: Flow<String>,
) {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)

    LaunchedEffect(deepLinkFlow) {
        deepLinkFlow.collect { uri ->
            when {
                // /recipes/saved 경로가 포함된 경우 (myapp://recipes/saved 또는 https://.../recipes/saved)
                uri.contains("/recipes/saved") -> {
                    topLevelBackStack.clear()
                    topLevelBackStack.add(Route.Main(startDestination = Route.SavedRecipes))
                }
                
                // 상세 화면 처리
                // 1. myapp://recipes/detail/{id}
                uri.contains("/recipes/detail/") -> {
                    val recipeId = uri.substringAfter("/recipes/detail/").toLongOrNull() ?: 0L
                    topLevelBackStack.clear()
                    topLevelBackStack.add(Route.Main())
                    topLevelBackStack.add(Route.RecipeDetail(recipeId))
                }
                
                // 2. https://neouul-recipe.web.app/recipes/{id} (숫자로 끝나는 경우)
                uri.matches(Regex(".*/recipes/\\d+$")) -> {
                    val recipeId = uri.substringAfterLast("/").toLongOrNull() ?: 0L
                    topLevelBackStack.clear()
                    topLevelBackStack.add(Route.Main())
                    topLevelBackStack.add(Route.RecipeDetail(recipeId))
                }
            }
        }
    }

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        backStack = topLevelBackStack,
        entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashRoot(
                    onNavigateToSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    }
                )
            }
            entry<Route.SignIn> {
                SignInRoot(
                    onNavigateToMain = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main())
                    },
                    onNavigateToSignUp = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignUp)
                    },
                )
            }
            entry<Route.SignUp> {
                SignUpRoot(
                    onNavigateToMain = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main())
                    },
                    onNavigateToSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    },
                )
            }
            // Home, Saved, Notifications, Profile
            entry<Route.Main> { key ->
                val mainBackStack = rememberNavBackStack(key.startDestination)

                MainScreen(
                    backStack = mainBackStack,
                    body = { modifier, showSnackbar ->
                        NavDisplay(
                            modifier = modifier,
                            backStack = mainBackStack,
                            entryProvider = entryProvider {
                                entry<Route.Home> {
                                    HomeRoot(
                                        onNavigateToSearch = {
                                            topLevelBackStack.add(Route.SearchRecipes)
                                        },
                                        onNavigateToProfile = {
                                            mainBackStack.clear()
                                            mainBackStack.add(Route.Profile)
                                        },
                                        onNavigateToRecipeDetail = { recipeId ->
                                            topLevelBackStack.add(Route.RecipeDetail(recipeId))
                                        }
                                    )
                                }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesRoot(
                                        onCardClick = { recipeId ->
                                            topLevelBackStack.add(Route.RecipeDetail(recipeId))
                                        },
                                        onReachedBottom = {
                                            showSnackbar("스크롤이 맨 아래에 도달했습니다.")
                                        },
                                    )
                                }
                                entry<Route.Notifications> {

                                }
                                entry<Route.Profile> {

                                }
                            }
                        )
                    }
                )
            }
            entry<Route.SearchRecipes> {
                SearchRecipesRoot(
                    onNavigateToBack = {
                        if (topLevelBackStack.size > 1) {
                            topLevelBackStack.removeAt(topLevelBackStack.lastIndex)
                        }
                    },
                    onNavigateToRecipeDetail = { recipeId ->
                        topLevelBackStack.add(Route.RecipeDetail(recipeId))
                    }
                )
            }
            entry<Route.RecipeDetail> { key ->
                IngredientRoot(
                    recipeId = key.recipeId,
                    onBackClick = {
                        if (topLevelBackStack.size > 1) {
                            topLevelBackStack.removeAt(topLevelBackStack.lastIndex)
                        }
                    }
                )
            }
        }
    )
}