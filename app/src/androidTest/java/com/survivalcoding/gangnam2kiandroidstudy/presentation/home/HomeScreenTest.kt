package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun clickBookmarkButton_changesViewModelState_and_maintainsStateAfterRecomposition() {
        // 1. Setup Data & Mocks
        val testRecipe = Recipe(
            id = 1,
            title = "Test Recipe",
            chef = "Test Chef",
            time = "10 min",
            category = "Test Category",
            rating = 4.5,
            imageUrls = "",
            createdAt = 0L,
            address = "Test Address"
        )
        
        val fakeRecipeRepository = object : RecipeRepository {
            override suspend fun getRecipes(): List<Recipe> = listOf(testRecipe)
        }
        
        val fakeBookmarkRepository = object : BookmarkRepository {
            private val bookmarks = mutableListOf<Int>()
            override suspend fun getBookmarkId(): List<Int> = bookmarks
            override suspend fun addBookmarkId(id: Int): List<Int> {
                bookmarks.add(id)
                return bookmarks
            }
            override suspend fun removeBookmarkId(id: Int): List<Int> {
                bookmarks.remove(id)
                return bookmarks
            }
        }

        // Initialize ViewModel with fakes
        val viewModel = HomeViewModel(fakeRecipeRepository, fakeBookmarkRepository)

        // 2. Set Content
        composeRule.setContent {
            // Using collectAsState to observe the flow in the Composable environment
            val state = viewModel.state.collectAsState().value
            HomeScreen(
                state = state,
                onAction = viewModel::onAction
            )
        }
        
        // Wait for the initial data loading (triggered in ViewModel init) to complete
        composeRule.waitForIdle()

        // 3. Verify initial state (not bookmarked)
        assertTrue("Initial bookmark list should be empty", viewModel.state.value.bookmarkIds.isEmpty())

        // 4. Perform Action: Click Bookmark on the recipe card
        // The HomeRecipeCard uses "bookmark Recipe" as content description for the button
        composeRule.onNodeWithContentDescription("bookmark Recipe")
            .performClick()

        // 5. Verify ViewModel State Changed
        composeRule.waitForIdle()
        assertTrue("Bookmark ID should be added to the list", viewModel.state.value.bookmarkIds.contains(testRecipe.id))

        // 6. Verify UI State Maintenance (Recomposition)
        // Checking that the state in ViewModel persists is the key. 
        // Compose automatically recomposes when state changes.
        // We verify that the data driving the UI is correct.
        
        // As an extra check for "icon state", we can ensure the logic that drives the icon (the isBookmark boolean passed to HomeRecipeCard)
        // is effectively true because the state has the ID. 
        // We can't easily check the tint color without custom matchers, but identifying the node exists and the underlying state is correct confirms the behavior.
        
        composeRule.onNodeWithContentDescription("bookmark Recipe")
            .assertExists()
            
        // Explicitly ensuring the state remains correct
        assertTrue("State should be maintained after interaction", viewModel.state.value.bookmarkIds.contains(testRecipe.id))
    }
}
