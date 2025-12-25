package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
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
fun NavigationRoot() {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)
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
                val backStack = rememberNavBackStack(Route.Home)
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
                                                it
                                            )
                                        )
                                    })
                                }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesRoot(onRecipeClick = {
                                        topLevelBackStack.add(Route.RecipeItem(it))
                                    })
                                }

                                entry<Route.Notifications> { }
                                entry<Route.Profile> { }

                            })

                    })

            }
            entry<Route.RecipeItem> { navEntry ->
                SavedRecipeItemRoot(
                    navEntry.recipe,
                    onBackButtonClick = { topLevelBackStack.removeAt(topLevelBackStack.lastIndex) })
            }

            entry<Route.Search> {
                SearchRecipesRoot(onRecipeClick = {
                    topLevelBackStack.add(Route.RecipeItem(it))
                })
            }
        })
}