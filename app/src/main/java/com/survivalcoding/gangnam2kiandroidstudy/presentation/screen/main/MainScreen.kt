package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.rounting.Route

@Composable
fun MainScreen(
    modifier: Modifier,
    backStack: NavBackStack<NavKey>,
    body: @Composable () -> Unit
) {
    val currentBackStack = backStack

    Scaffold(
        bottomBar = {
            NavigationBar{
                NavigationBarItem(
                    selected = currentBackStack==Route.Home,
                    onClick = {
                        backStack.clear()
                        backStack.add(Route.Home)
                     },
                    icon = {
                        Icon(painter = painterResource(R.drawable.outline_home),"홈화면")
                    },

                )
                NavigationBarItem(
                    selected = currentBackStack==Route.SavedRecipes,
                    onClick = {
                        backStack.clear()
                        backStack.add(Route.SavedRecipes)
                    },
                    icon = {
                        Icon(painter = painterResource(R.drawable.outline_bookmark_inactive),"홈화면")
                    },

                    )
                NavigationBarItem(
                    selected = currentBackStack==Route.Notifications,
                    onClick = {
                        backStack.clear()
                        backStack.add(Route.Notifications)
                    },
                    icon = {
                        Icon(painter = painterResource(R.drawable.outline_notification_bing),"홈화면")
                    },

                    )
                NavigationBarItem(
                    selected = currentBackStack==Route.Profile,
                    onClick = {
                        backStack.clear()
                        backStack.add(Route.Profile)
                    },
                    icon = {
                        Icon(painter = painterResource(R.drawable.outline_profile),"홈화면")
                    },

                    )
            }


        }
    ){paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            body() // MainScreen의 주 내용을 호출합니다.
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    //MainScreen()

}