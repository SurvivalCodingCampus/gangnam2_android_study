package com.survivalcoding.gangnam2kiandroidstudy.presentation.navigation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.survivalcoding.gangnam2kiandroidstudy.MainActivity
import com.survivalcoding.gangnam2kiandroidstudy.core.di.NetworkModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.RepositoryModule
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkEvent
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkStatus
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.HomeImage
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesRoot
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import javax.inject.Singleton

@HiltAndroidTest
@UninstallModules(RepositoryModule::class, NetworkModule::class)
class SearchToDetailNavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Module
    @InstallIn(SingletonComponent::class)
    object NavigationTestRepositoryModule {

        @Provides
        @Singleton
        fun provideRecipeRepository(): RecipeRepository {
            return object : RecipeRepository {
                private val recipes = listOf(
                    Recipe(
                        id = 1,
                        category = "Cereal",
                        title = "Traditional spare ribs baked",
                        imageUrls = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                        chefId = 1,
                        chefName = "Chef John",
                        time = "20 min",
                        rating = 4.0,
                        createdAt = 1736034600000,
                        homeImage = HomeImage.FOOD1,
                    )
                )

                override suspend fun getRecipes(): List<Recipe> = recipes
                override suspend fun getRecipeById(id: Int): Recipe? = recipes.find { it.id == id }
                override suspend fun getRecipesByIds(ids: List<Int>): List<Recipe> =
                    recipes.filter { it.id in ids }
            }
        }

        @Provides
        @Singleton
        fun provideChefRepository(): ChefRepository {
            return object : ChefRepository {
                override suspend fun getChefById(id: Int): Chef? {
                    return if (id == 1) Chef(1, "Chef John", "", "Lagos") else null
                }
            }
        }

        @Provides
        @Singleton
        fun provideIngredientRepository(): IngredientRepository {
            return object : IngredientRepository {
                override suspend fun getIngredientsByRecipeId(recipeId: Int): List<IngredientItem> {
                    // Return dummy ingredients for testing detail screen
                    return listOf(
                        IngredientItem(1, "Tomatos", "", 500),
                        IngredientItem(2, "Beef", "", 1000)
                    )
                }
            }
        }

        @Provides
        @Singleton
        fun provideProcedureRepository(): ProcedureRepository {
            return object : ProcedureRepository {
                override suspend fun getProceduresByRecipeId(recipeId: Int): List<Procedure> {
                    return listOf(
                        Procedure(1, 1, "Preheat the oven."),
                        Procedure(1, 2, "Season the ribs.")
                    )
                }
            }
        }

        @Provides
        @Singleton
        fun provideBookmarkRepository(): BookmarkRepository {
            return object : BookmarkRepository {
                override fun getSavedRecipeIds(): Flow<List<Int>> = flowOf(emptyList())
                override suspend fun addBookmark(recipeId: Int) {}
                override suspend fun removeBookmark(recipeId: Int) {}
                override suspend fun isBookmarked(recipeId: Int): Boolean = false
            }
        }

        // 테스트에서 직접 쓰지 않아도앱이 참조하는 모든 의존성은 반드시 주입 가능해야 함
        @Provides
        @Singleton
        fun provideNetworkMonitor(): NetworkMonitor {
            return object : NetworkMonitor {
                override val events: Flow<NetworkEvent> = flow { }
                override val status: StateFlow<NetworkStatus> =
                    // 항상 안정 상태로 고정하여 테스트 노이즈 제거 목적
                    MutableStateFlow(NetworkStatus.Available)
            }
        }
    }

    @Test
    fun navigate_from_search_to_detail_and_back() {
        hiltRule.inject()

        composeRule.activity.setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "search") {
                composable("search") {
                    SearchRecipesRoot(
                        onNavigateToRecipeDetail = { recipeId ->
                            navController.navigate("detail/$recipeId")
                        },
                        onBack = {
                            // Handle back from search if needed (not tested here)
                        }
                    )
                }
                composable(
                    route = "detail/{recipeId}",
                    arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
                    IngredientRoot(
                        recipeId = recipeId,
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }

        composeRule.waitForIdle()

        // 1. Verify we are on Search Screen and item exists
        composeRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()

        // 2. Click the item
        composeRule.onNodeWithText("Traditional spare ribs baked").performClick()

        // 3. Verify we navigated to Detail Screen
        composeRule.waitForIdle()
        composeRule.onNodeWithText("Chef John").assertIsDisplayed()
        composeRule.onNodeWithText("Tomatos").assertIsDisplayed() // From fake ingredient

        // 4. Verify Back Navigation
        composeRule.activityRule.scenario.onActivity { activity ->
            activity.onBackPressedDispatcher.onBackPressed()
        }

        // 5. Verify we are back on Search Screen
        composeRule.waitForIdle()
        // Wait until "Tomatos" does not exist
        composeRule.waitUntil(timeoutMillis = 3000) {
            composeRule.onAllNodesWithText("Tomatos").fetchSemanticsNodes().isEmpty()
        }

        composeRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()
    }
}
