package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.detail.RecipeDetailRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in.SignInRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up.SignUpRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashRoot

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(Route.Splash)

    fun reset(route: Route) {
        backStack.clear()
        backStack.add(route)
    }

    fun push(route: Route) {
        backStack.add(route)
    }

    fun pop() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
    }

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {

            entry<Route.Splash> {
                SplashRoot(
                    onStartClick = { reset(Route.SignIn) }
                )
            }

            entry<Route.SignIn> {
                SignInRoot(
                    onSignUpClick = { reset(Route.SignUp) },
                    onSignInSuccess = { reset(Route.Main) }
                )
            }

            entry<Route.SignUp> {
                SignUpRoot(
                    onBackToSignIn = { reset(Route.SignIn) },
                    onSignUpSuccess = { reset(Route.SignIn) }
                )
            }

            // Main 탭 Root (Home / Saved / Profile 등은 MainRoot 내부에서 관리)
            entry<Route.Main> {
                MainRoot(
                    onOpenSearch = { push(Route.SearchRecipes) },
                    onOpenRecipeDetail = { recipeId ->
                        push(Route.RecipeDetail(recipeId))
                    }
                )
            }

            // Search (Top-level)
            entry<Route.SearchRecipes> {
                SearchRecipeRoot(
                    onBack = { pop() },
                )
            }

            entry<Route.RecipeDetail> { route ->
                RecipeDetailRoot(
                    recipeId = route.recipeId,
                    onBack = { pop() }
                )
            }
        }
    )
}
