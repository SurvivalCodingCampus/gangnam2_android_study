package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail.SavedRecipeItemRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes.SavedRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashRoot

@Composable
fun NavigationRoot(deepLinkUri: String?) {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)
    val backStack = rememberNavBackStack(Route.Home)

    LaunchedEffect(deepLinkUri) {
        if (deepLinkUri != null) {
            val uri = deepLinkUri.toUri()
            // 호스트 확인 (커스텀 스킴 또는 Firebase 호스팅 도메인)
            val isValidHost = (uri.scheme == "app" && uri.host == "recipe.misterjerry.com") ||
                              ((uri.scheme == "https" || uri.scheme == "http") && uri.host == "misterjerry-androidstudy.web.app")

            if (isValidHost) {
                // 경로 처리 (/saved 또는 /saved/{id} 등)
                // 예: https://my-app.web.app/saved -> path: /saved
                if (uri.path == "/saved") {
                    topLevelBackStack.clear()
                    backStack.clear()

                    topLevelBackStack.add(Route.Main)
                    backStack.add(Route.SavedRecipes)
                } else {
                    // 숫자로 된 ID가 경로의 마지막에 있는지 확인
                    val recipeId = uri.lastPathSegment?.toIntOrNull()

                    if (recipeId != null) {
                        topLevelBackStack.clear()
                        backStack.clear()

                        topLevelBackStack.add(Route.Main)
                        backStack.add(Route.SavedRecipes)
                        topLevelBackStack.add(Route.RecipeItem(recipeId))
                    }
                }
            }
        }
    }
    NavDisplay(
        backStack = topLevelBackStack, entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ), entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashRoot(
                    onStartButtonClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    },
                )
            }
            entry<Route.SignUp> {
                SignUpScreen(
                    onSignInButtonClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    })
            }
            entry<Route.SignIn> {
                SignInScreen(onSignUpButtonClick = {
                    topLevelBackStack.clear()
                    topLevelBackStack.add(Route.SignUp)
                }, onSignInButtonClick = {
                    topLevelBackStack.clear()
                    topLevelBackStack.add(Route.Main)
                })
            }

            entry<Route.Main> {
                MainScreen(
                    backStack = backStack, body = {
                        NavDisplay(
                            backStack = backStack, entryDecorators = listOf(
                                rememberSaveableStateHolderNavEntryDecorator(),
                                rememberViewModelStoreNavEntryDecorator()
                            ), entryProvider = entryProvider {
                                entry<Route.Home> {
                                    HomeRoot(onSearchClicked = {
                                        topLevelBackStack.add(
                                            Route.Search
                                        )
                                    }, onRecipeItemClicked = {
                                        topLevelBackStack.add(
                                            Route.RecipeItem(
                                                it.id
                                            )
                                        )
                                    })
                                }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesRoot(onRecipeClick = {
                                        topLevelBackStack.add(Route.RecipeItem(it.id))
                                    })
                                }

                                entry<Route.Notifications> { }
                                entry<Route.Profile> { }

                            })

                    })

            }
            entry<Route.RecipeItem> { navEntry ->
                SavedRecipeItemRoot(
                    navEntry.recipeId,
                    onBackButtonClick = { topLevelBackStack.removeAt(topLevelBackStack.lastIndex) })
            }

            entry<Route.Search> {
                SearchRecipesRoot(onRecipeClick = {
                    topLevelBackStack.add(Route.RecipeItem(it.id))
                })
            }
        })
}