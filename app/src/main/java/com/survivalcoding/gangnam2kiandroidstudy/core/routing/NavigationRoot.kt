package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipeDetail.RecipeDetailScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe.SavedRecipesScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchRecipeScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in.SignInScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up.SignUpScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.splash.SplashScreenRoot

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    deepLinkUri: String? = null
) {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)
    val backStack = rememberNavBackStack(Route.Home)

    LaunchedEffect(deepLinkUri) {
        if (deepLinkUri != null) {
            val uri = deepLinkUri.toUri()
            when {
                uri.path == "/saved" -> {
                    topLevelBackStack.clear()
                    backStack.clear()

                    topLevelBackStack.add(Route.Main)
                    backStack.add(Route.SavedRecipes)
                }

                uri.path?.startsWith("/detail") == true -> {
                    val recipeId = uri.lastPathSegment?.toIntOrNull()
                    if (recipeId != null) {

                        topLevelBackStack.clear()
                        backStack.clear()

                        topLevelBackStack.add(Route.Main)
                        backStack.add(Route.SavedRecipes)
                        topLevelBackStack.add(Route.RecipeDetail(recipeId))
                    }
                }

                uri.path == "/recipes/saved" -> {
                    topLevelBackStack.clear()
                    backStack.clear()

                    topLevelBackStack.add(Route.Main)
                    backStack.add(Route.SavedRecipes)
                }

                uri.path?.startsWith("/recipes/") == true -> {
                    val recipeId = uri.lastPathSegment?.toIntOrNull()
                    if (recipeId != null) {
                        topLevelBackStack.clear()
                        backStack.clear()

                        topLevelBackStack.add(Route.Main)
                        backStack.add(Route.SavedRecipes)
                        topLevelBackStack.add(Route.RecipeDetail(recipeId))
                    }
                }
            }
        }
    }
    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = topLevelBackStack,
        entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashScreenRoot(
                    onNavigateToSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    }
                )
            }
            entry<Route.SignIn> {
                SignInScreenRoot(
                    onNavigateToSignUp = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignUp)
                    },
                    onNavigateToHome = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main)
                    }
                )

            }
            entry<Route.SignUp> {
                SignUpScreenRoot(
                    onNavigateToSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    },
                    onNavigateToHome = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main)
                    }
                )
            }
            entry<Route.Main> {

                MainScreen(
                    backStack = backStack,
                    body = {
                        NavDisplay(
                            modifier = modifier,
                            backStack = backStack,
                            entryDecorators = listOf(
                                rememberSaveableStateHolderNavEntryDecorator(),
                                rememberViewModelStoreNavEntryDecorator()
                            ),
                            entryProvider = entryProvider {
                                entry<Route.Home> {
                                    HomeScreenRoot(
                                        onSearchClick = {
                                            topLevelBackStack.add(Route.SearchRecipe)
                                        },
                                        onRecipeClick = {
                                            topLevelBackStack.add(Route.RecipeDetail(recipeId = it))
                                        }
                                    )
                                }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesScreenRoot(
                                        onCardClick = { recipeId ->
                                            topLevelBackStack.add(Route.RecipeDetail(recipeId = recipeId))
                                        }
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
            entry<Route.RecipeDetail> { route ->
                RecipeDetailScreenRoot(
                    recipeId = route.recipeId,
                    onBackClick = {
                        topLevelBackStack.remove(topLevelBackStack.last())
                    }
                )
            }

            entry<Route.SearchRecipe> {
                SearchRecipeScreenRoot(
                    onRecipeClick = { recipeId ->
                        topLevelBackStack.add(Route.RecipeDetail(recipeId = recipeId))
                    }
                )
            }

        }
    )

}