package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.navigation.BottomNavBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.navigation.bottomNavItemList
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notifications.NotificationsScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile.ProfileScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.SavedRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun MainRoot(
    deepLinkUri: String? = null,
    onDeepLinkHandled: () -> Unit = {}
) {
    // Activity 시작 및 리소스 접근을 위해 Context 사용
    val context = androidx.compose.ui.platform.LocalContext.current
    // 1. 초기 스택 생성
    val mainBackStack = rememberNavBackStack(Route.Home)

    // 2. deepLinkUri가 들어오면 즉시 스택 조작
    LaunchedEffect(deepLinkUri) {
        if (deepLinkUri != null) {
            val uri = deepLinkUri.toUri()
            if (uri.scheme == "https" || uri.scheme == "myapp") {
                val pathSegments = uri.pathSegments

                // "saved" 문자열이 경로 어디에든 포함되어 있는지 확인
                if (pathSegments.contains("saved")) {
                    // Compose 화면(SavedRecipesRoot) 대신 기존 Activity를 실행하도록 가로챔
                    val intent = android.content.Intent(
                        context,
                        com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.SavedRecipesActivity::class.java
                    )
                    context.startActivity(intent)
                    onDeepLinkHandled()
                }
                // "detail" 문자열을 찾고, 그 바로 다음 인덱스의 값을 ID로 사용
                else if (pathSegments.contains("detail")) {
                    val detailIndex = pathSegments.indexOf("detail")
                    val recipeIdRaw = pathSegments.getOrNull(detailIndex + 1)
                    val recipeId = recipeIdRaw?.toIntOrNull()

                    if (recipeId != null) {
                        mainBackStack.clear()
                        mainBackStack.add(Route.Home) // 뒤로가기 베이스
                        mainBackStack.add(Route.RecipeDetail(recipeId))
                        onDeepLinkHandled()
                    }
                }
            }
        }
    }

    val currentKey = mainBackStack.lastOrNull() ?: Route.Home

    val showBottomBar = currentKey in listOf(
        Route.Home,
        Route.SavedRecipes,
        Route.Notifications,
        Route.Profile
    )

    Scaffold(
        containerColor = AppColors.white,
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    items = bottomNavItemList,
                    currentRoute = currentKey,
                    onItemClick = { route ->
                        // SavedRecipes 탭은 Compose 화면이 아닌 Activity로 구현되어 있어 별도 처리
                        if (route == Route.SavedRecipes) {
                            val intent = android.content.Intent(
                                context,
                                com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.SavedRecipesActivity::class.java
                            )
                            context.startActivity(intent)
                            return@BottomNavBar // Compose 네비게이션 스택을 변경하지 않음
                        }
                        if (route != currentKey) {
                            mainBackStack.clear()
                            mainBackStack.add(route)
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = mainBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {

                entry<Route.Home> {
                    HomeRoot(
                        onNavigateToSearch = {
                            mainBackStack.add(Route.SearchRecipes)
                        },
                        onNavigateToRecipeDetail = { recipeId ->
                            mainBackStack.add(Route.RecipeDetail(recipeId))
                        }
                    )
                }

                entry<Route.SavedRecipes> {
                    SavedRecipesRoot(
                        onNavigateToRecipeDetail = { recipeId ->
                            mainBackStack.add(Route.RecipeDetail(recipeId))
                        }
                    )
                }

                entry<Route.Profile> {
                    ProfileScreen()
                }

                entry<Route.Notifications> {
                    NotificationsScreen()
                }

                entry<Route.SearchRecipes> {
                    SearchRecipesRoot(
                        onNavigateToRecipeDetail = { recipeId ->
                            mainBackStack.add(Route.RecipeDetail(recipeId))
                        },
                        onBack = {
                            if (mainBackStack.size > 1) {
                                mainBackStack.removeAt(mainBackStack.lastIndex)
                            }
                        }
                    )
                }
                entry<Route.RecipeDetail> { route ->
                    IngredientRoot(
                        recipeId = route.recipeId,
                        onBack = {
                            if (mainBackStack.size > 1) {
                                mainBackStack.removeAt(mainBackStack.lastIndex)
                            }
                        }
                    )
                }
            }
        )
    }
}
