package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import org.junit.Rule
import org.junit.Test

class HomeScreenErrorTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val bookmarkRepository = object : BookmarkRepository {
        override suspend fun getBookmarkId(): List<Int> = emptyList()
        override suspend fun addBookmarkId(id: Int): List<Int> = emptyList()
        override suspend fun removeBookmarkId(id: Int): List<Int> = emptyList()
    }

    @Test
    fun loadRecipesSuccess_displaysRecipeList() {
        // 1. Setup Success Repository
        val testRecipe = Recipe(
            id = 1, title = "Success Recipe", chef = "Chef", time = "10m",
            category = "Asian", rating = 5.0, imageUrls = "", createdAt = 0L, address = ""
        )
        val successRepository = object : RecipeRepository {
            override suspend fun getRecipes(): List<Recipe> = listOf(testRecipe)
        }

        val viewModel = HomeViewModel(successRepository, bookmarkRepository)

        // 2. Set Content
        composeRule.setContent {
            val state = viewModel.state.collectAsState().value
            HomeScreen(state = state, onAction = viewModel::onAction)
        }

        // 3. Verify Recipe Title is displayed (at least once)
        composeRule.onAllNodesWithText("Success Recipe").onFirst().assertIsDisplayed()
        composeRule.onNodeWithText("Failed to load recipes").assertDoesNotExist()
    }

    @Test
    fun loadRecipesFailure_displaysErrorMessage() {
        // 1. Setup Failure Repository (Throws Exception)
        val failureRepository = object : RecipeRepository {
            override suspend fun getRecipes(): List<Recipe> {
                throw Exception("Network Error")
            }
        }

        val viewModel = HomeViewModel(failureRepository, bookmarkRepository)

        // 2. Set Content
        composeRule.setContent {
            val state = viewModel.state.collectAsState().value
            HomeScreen(state = state, onAction = viewModel::onAction)
        }

        // 3. Verify Error Message is displayed
        composeRule.onNodeWithText("Failed to load recipes").assertIsDisplayed()
        // Ensure success UI elements (like a specific recipe title) are not shown
        composeRule.onNodeWithText("Success Recipe").assertDoesNotExist()
    }
}
