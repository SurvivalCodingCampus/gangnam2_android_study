package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes.SavedRecipesScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashScreen

@Composable
fun NavigationRoot() {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)
    NavDisplay(
        backStack = topLevelBackStack, entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ), entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashScreen(
                    onStartButtonClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    })
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
                                entry<Route.Home> { HomeRoot() }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesScreen()
                                }
                                entry<Route.Notifications> { HomeRoot() }
                                entry<Route.Profile> { HomeRoot() }
                            })
                    })

            }
        })
}