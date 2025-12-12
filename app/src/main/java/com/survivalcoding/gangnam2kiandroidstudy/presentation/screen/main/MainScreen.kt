package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.NavigationBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot

@Composable
fun MainScreen(
    body: @Composable (modifier: Modifier) -> Unit,
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
) {
    val currentRoute = backStack.lastOrNull()

    Scaffold(
        bottomBar = {
            NavigationBar(currentRoute = currentRoute)
        }
    ) {
        body(modifier)
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    val mainBackStack = rememberNavBackStack(Route.Home)

    MainScreen(
        body = {
            NavDisplay(
                backStack = mainBackStack,
                entryProvider = entryProvider {
                    entry<Route.Home> {
                        HomeRoot(
                            onSearchClick = {},
                            onProfileClick = {},
                        )
                    }
                    entry<Route.SavedRecipes> {

                    }
                    entry<Route.Notifications> {

                    }
                    entry<Route.Profile> {

                    }
                }
            )
        },
        backStack = mainBackStack,
    )
}