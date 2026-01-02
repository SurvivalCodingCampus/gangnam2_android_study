package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import com.survivalcoding.gangnam2kiandroidstudy.MainActivity
import com.survivalcoding.gangnam2kiandroidstudy.R
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
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidTest
@UninstallModules(RepositoryModule::class, NetworkModule::class)
class HomeScreenBookmarkTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var bookmarkRepository: BookmarkRepository

    @Module
    @InstallIn(SingletonComponent::class)
    object HomeTestModule {

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
        fun provideBookmarkRepository(): BookmarkRepository {
            return object : BookmarkRepository {
                private val bookmarks = MutableStateFlow<Set<Int>>(emptySet())

                override fun getSavedRecipeIds(): Flow<List<Int>> = bookmarks.map { it.toList() }

                override suspend fun addBookmark(recipeId: Int) {
                    bookmarks.update { it + recipeId }
                }

                override suspend fun removeBookmark(recipeId: Int) {
                    bookmarks.update { it - recipeId }
                }

                override suspend fun isBookmarked(recipeId: Int): Boolean =
                    bookmarks.value.contains(recipeId)
            }
        }

        @Provides
        @Singleton
        fun provideChefRepository(): ChefRepository {
            return object : ChefRepository {
                override suspend fun getChefById(id: Int): Chef? = null
            }
        }

        @Provides
        @Singleton
        fun provideIngredientRepository(): IngredientRepository {
            return object : IngredientRepository {
                override suspend fun getIngredientsByRecipeId(recipeId: Int): List<IngredientItem> =
                    emptyList()
            }
        }

        @Provides
        @Singleton
        fun provideProcedureRepository(): ProcedureRepository {
            return object : ProcedureRepository {
                override suspend fun getProceduresByRecipeId(recipeId: Int): List<Procedure> =
                    emptyList()
            }
        }

        @Provides
        @Singleton
        fun provideNetworkMonitor(): NetworkMonitor {
            return object : NetworkMonitor {
                override val events: Flow<NetworkEvent> = flow { }
                override val status: StateFlow<NetworkStatus> =
                    MutableStateFlow(NetworkStatus.Available)
            }
        }
    }

    @Test
    fun bookmark_persists_after_rotation_full_flow() {
        hiltRule.inject()

        // MainActivity launches automatically -> Splash Screen

        // 1. Splash Screen: Click "Get Started"
        // Wait for Splash Button
        val splashButtonText = composeRule.activity.getString(R.string.splash_button)
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithText(splashButtonText).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText(splashButtonText).performClick()

        // 2. SignIn Screen: Input credentials and Click "Sign In"
        val signInButtonText = composeRule.activity.getString(R.string.button_sign_in)
        composeRule.activity.getString(R.string.placeholder_email)
        composeRule.activity.getString(R.string.placeholder_password)

        // Wait for SignIn Screen
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithText(signInButtonText).fetchSemanticsNodes().isNotEmpty()
        }

        // Input Email & Password
        // Use placeholder to find the text field
        composeRule
            .onAllNodesWithTag("email_input")
            .onLast()
            .performTextInput("test@test.com")
        composeRule
            .onAllNodesWithTag("password_input")
            .onLast()
            .performTextInput("password")


        // Click Sign In
        composeRule.onNodeWithText(signInButtonText).performClick()

        // 3. Home Screen: Wait for Data Load
        // Wait for "Bookmark Outline" to appear (indicates data loaded)
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithContentDescription("Bookmark Outline").fetchSemanticsNodes()
                .isNotEmpty()
        }

        // 4. Click Bookmark Button
        composeRule.onNodeWithContentDescription("Bookmark Outline").performClick()
        composeRule.waitForIdle()

        // 5. Verify UI State Change: Filled
        composeRule.onNodeWithContentDescription("Bookmark Filled").assertIsDisplayed()

        // 6. Rotate Screen
        composeRule.activityRule.scenario.recreate()
        composeRule.waitForIdle()

        // 7. Verify Persistence on Home Screen
        // Since we followed the real app flow, the NavHost (rememberSaveable) should restore the Home Screen.
        // We do NOT setContent again. We assert directly on the restored UI.

        // Wait for Home Screen to be restored (might take a split second)
        composeRule.waitUntil(timeoutMillis = 5000) {
            // Check if we are still on Home Screen (Bookmark Filled should be visible)
            composeRule.onAllNodesWithContentDescription("Bookmark Filled").fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeRule.onNodeWithContentDescription("Bookmark Filled").assertIsDisplayed()

        // Also verify data logic
        runBlocking {
            val savedAfterRotation = bookmarkRepository.getSavedRecipeIds().first()
            assertTrue(
                "Bookmark should persist in repository after rotation",
                savedAfterRotation.contains(1)
            )
        }
    }
}