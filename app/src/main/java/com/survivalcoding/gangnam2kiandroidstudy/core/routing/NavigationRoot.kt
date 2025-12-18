package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient.IngredientScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe.SavedRecipesScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchRecipeScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.splash.SplashScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = topLevelBackStack,
        entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashScreen(
                    onClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    }
                )
            }
            entry<Route.SignIn> {
                SignInScreen(
                    onSignUpClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignUp)
                    },
                    onLoginClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main)
                    }
                )

            }
            entry<Route.SignUp> {
                SignUpScreen(
                    onSignInClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    }
                )

            }
            entry<Route.Main> {
                val backStack = rememberNavBackStack(Route.Home)

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
                                        }
                                    )
                                }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesScreenRoot(
                                        onCardClick = { recipeId ->
                                            topLevelBackStack.add(Route.Ingrident(recipeId = recipeId))
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
            entry<Route.Ingrident> { route ->
                IngredientScreenRoot(recipeId = route.recipeId)
            }

            entry<Route.SearchRecipe> {
                SearchRecipeScreenRoot()
            }

        }
    )

}