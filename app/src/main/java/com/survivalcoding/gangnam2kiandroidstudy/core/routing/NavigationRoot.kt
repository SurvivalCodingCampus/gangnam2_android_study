package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in.SignInRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up.SignUpRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashRoot

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)

    NavDisplay(
        modifier = modifier, backStack = topLevelBackStack, entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ), entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashRoot(
                    onNavigateToSignIn = {
                        topLevelBackStack.replaceAll { Route.SignIn }
                    }
                )
            }
            entry<Route.SignIn> {
                SignInRoot(
                    onNavigateToHome = {
                        topLevelBackStack.replaceAll { Route.Main }
                    },
                    onNavigateToSignUp = {
                        topLevelBackStack.add(Route.SignUp)
                    }
                )
            }

            entry<Route.SignUp> {
                SignUpRoot(
                    onNavigateToHome = {
                        topLevelBackStack.replaceAll { Route.Main }
                    },
                    onNavigateToSignIn = {
                        topLevelBackStack.add(Route.SignIn)
                    }
                )
            }

            entry<Route.Main> {
                MainRoot()
            }
        }
    )
}
