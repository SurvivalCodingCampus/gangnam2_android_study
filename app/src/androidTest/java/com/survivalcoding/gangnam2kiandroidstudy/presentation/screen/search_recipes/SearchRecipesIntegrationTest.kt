package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import com.survivalcoding.gangnam2kiandroidstudy.MainActivity
import com.survivalcoding.gangnam2kiandroidstudy.core.di.RepositoryModule
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
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
import org.junit.Rule
import org.junit.Test
import javax.inject.Singleton

@HiltAndroidTest
@UninstallModules(RepositoryModule::class)
class SearchRecipesIntegrationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Module
    @InstallIn(SingletonComponent::class)
    object TestRepositoryModule {

        @Provides
        @Singleton
        fun provideRecipeRepository(): RecipeRepository {
            return FakeRecipeRepository()
        }

        @Provides
        @Singleton
        fun provideBookmarkRepository(): BookmarkRepository {
            return object : BookmarkRepository {
                override suspend fun getSavedRecipeIds(): List<Int> = emptyList()
                override suspend fun addBookmark(recipeId: Int) {}
                override suspend fun removeBookmark(recipeId: Int) {}
                override suspend fun isBookmarked(recipeId: Int): Boolean = false
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
    }

    @Test
    fun search_displays_filtered_results() {
        hiltRule.inject()

        composeRule.activity.setContent {
            val viewModel: SearchRecipesViewModel = hiltViewModel()
            val state = viewModel.state.collectAsState().value
            SearchRecipesScreen(
                state = state,
                onAction = viewModel::onAction
            )
        }

        // 1. Enter keyword "Chicken"
        // Adjust the text to match the placeholder or existing text in SearchBarContainer if necessary.
        // Assuming there is a text field.
        // Based on SearchRecipesScreen code: SearchBarContainer -> value = state.searchKeyword
        // We find the TextField. Since it's likely the only one or has a specific placeholder.
        // Let's assume we can find it by type or just input text.
        // Better: Find by placeholder if known, but "Search" is a common guess.
        // Or find by tag. I don't see tags in the provided snippet.
        // I will try to find a TextField node.

        // Wait for idle before starting
        composeRule.waitForIdle()

        // Input "Chicken"
        // Note: performTextInput works on a node that accepts input.
        // Since SearchBarContainer wraps a TextField (presumably), finding the editable node is key.
        // I will try to find by specific text logic or generic class if needed, 
        // but typically finding by a placeholder or existing text is safe.
        // If the initial state is empty, maybe find by Semantics?
        // Let's try finding the only TextField if possible, or assume it's focusable.
        // Actually, let's look at the source code snippet of SearchBarContainer? 
        // Not provided fully, but `value` is passed.
        // I'll try simply `onNode(hasSetTextAction())` which finds input fields.
        composeRule.onNode(hasSetTextAction()).performTextInput("Chicken")

        // 2. Wait for Debounce (300ms) + Coroutine Dispatch
        // Wait until the node with text "Traditional spare ribs baked" disappears.
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule
                .onAllNodesWithText("Traditional spare ribs baked")
                .fetchSemanticsNodes().isEmpty()
        }

        // 3. Assert Results
        // "Spice roasted chicken with flavored rice" should be visible
        composeRule.onNodeWithText("Spice roasted chicken with flavored rice").assertIsDisplayed()

        // "Spicy fried rice mix chicken bali" should be visible (contains "chicken")
        composeRule.onNodeWithText("Spicy fried rice mix chicken bali").assertIsDisplayed()

        // "Traditional spare ribs baked" should NOT be visible
        composeRule.onNodeWithText("Traditional spare ribs baked").assertIsNotDisplayed()
    }
}
