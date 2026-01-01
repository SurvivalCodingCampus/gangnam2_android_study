package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeBookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.AddBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetBookmarkedRecipeIdsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.RemoveBookmarkUseCase
import org.junit.Rule
import org.junit.Test

class HomeIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadRecipes_success_showsData() {
        val fakeRecipeRepository = FakeRecipeRepository()
        val fakeBookmarkRepository = FakeBookmarkRepository()

        val viewModel = HomeViewModel(
            GetRecipesUseCase(fakeRecipeRepository),
            GetBookmarkedRecipeIdsUseCase(fakeBookmarkRepository),
            AddBookmarkUseCase(fakeBookmarkRepository),
            RemoveBookmarkUseCase(fakeBookmarkRepository)
        )

        composeTestRule.setContent {
            HomeRoot(
                onNavigateToSearch = {},
                onNavigateToProfile = {},
                onNavigateToRecipeDetail = {},
                viewModel = viewModel
            )
        }

        // Check if data is displayed
        composeTestRule.onNodeWithText("Hello Jega").assertIsDisplayed()
        composeTestRule.onAllNodesWithText("Test Recipe 1").onFirst().assertIsDisplayed()
    }

    @Test
    fun loadRecipes_error_showsErrorState() {
        val fakeRecipeRepository = FakeRecipeRepository()
        fakeRecipeRepository.shouldReturnError = true
        val fakeBookmarkRepository = FakeBookmarkRepository()

        val viewModel = HomeViewModel(
            GetRecipesUseCase(fakeRecipeRepository),
            GetBookmarkedRecipeIdsUseCase(fakeBookmarkRepository),
            AddBookmarkUseCase(fakeBookmarkRepository),
            RemoveBookmarkUseCase(fakeBookmarkRepository)
        )

        composeTestRule.setContent {
            HomeRoot(
                onNavigateToSearch = {},
                onNavigateToProfile = {},
                onNavigateToRecipeDetail = {},
                viewModel = viewModel
            )
        }

        // Check if error message is displayed
        composeTestRule.onNodeWithText("에러가 발생했습니다").assertIsDisplayed()
    }
}