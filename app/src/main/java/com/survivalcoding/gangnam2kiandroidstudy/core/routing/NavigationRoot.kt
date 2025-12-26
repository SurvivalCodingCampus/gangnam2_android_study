package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkEvent
import com.survivalcoding.gangnam2kiandroidstudy.presentation.debug.DebugNetworkPanel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main.AppRootViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in.SignInRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up.SignUpRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashRoot

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    deepLinkUri: String? = null,
    onDeepLinkHandled: () -> Unit = {}
) {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)

    val snackbarHostState = remember { SnackbarHostState() }
    val viewModel: AppRootViewModel = hiltViewModel()

    // NetworkMonitor 는 ViewModel 통해서만 사용
    val networkMonitor = viewModel.networkMonitor


    LaunchedEffect(Unit) {
        viewModel.networkEvents.collect { event ->
            when (event) {
                NetworkEvent.Disconnected ->
                    snackbarHostState.showSnackbar("네트워크 연결이 필요합니다")

                NetworkEvent.Connected ->
                    snackbarHostState.showSnackbar("네트워크가 연결되었습니다")
            }
        }
    }

    // 딥링크 존재 판단
    LaunchedEffect(deepLinkUri) {
        if (deepLinkUri != null) {
            val uri = deepLinkUri.toUri()
            if (uri.scheme == "myapp" && uri.host == "recipes") {
                topLevelBackStack.replaceAll { Route.Main }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding)
        ) {
            // 메인 네비게이션
            NavDisplay(
                backStack = topLevelBackStack,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {
                    entry<Route.Splash> {
                        SplashRoot(
                            onNavigateToSignIn = {
                                topLevelBackStack.add(Route.SignIn)
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
                        key(deepLinkUri) {
                            MainRoot(deepLinkUri = deepLinkUri)
                        }
                    }
                }
            )

            // dev / qa 전용 네트워크 패널
            DebugNetworkPanel(
                modifier = Modifier.align(Alignment.CenterEnd),
                networkMonitor = networkMonitor
            )
        }
    }
}


    
