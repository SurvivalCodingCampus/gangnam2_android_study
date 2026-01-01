package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.repository.InMemoryBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.recipe.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.AddBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetBookmarkedRecipeIdsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.RemoveBookmarkUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var fakeRepository: FakeRecipeRepository
    private lateinit var viewModel: HomeViewModel

    private val recipe1 = Recipe(
        id = 1,
        name = "Recipe 1",
        category = "Indian",
        time = "30 min",
        chef = "Chef 1",
        rating = 4.5,
        imageUrl = "",
        createdAt = 1L,
        address = "Seoul",
    )

    @Before
    fun setUp() {
        fakeRepository = FakeRecipeRepository().apply {
            addRecipe(recipe1)
        }

        val bookmarkRepository = InMemoryBookmarkRepositoryImpl()
        viewModel = HomeViewModel(
            repository = fakeRepository,
            addBookmarkUseCase = AddBookmarkUseCase(bookmarkRepository),
            removeBookmarkUseCase = RemoveBookmarkUseCase(bookmarkRepository),
            getBookmarkedRecipeIdsUseCase = GetBookmarkedRecipeIdsUseCase(bookmarkRepository),
        )
    }

    @Test
    fun bookmarkToggle_stateIsUpdated_andPersists() {
        viewModel.onAction(HomeAction.LoadHome)

        val recomposeTrigger = mutableStateOf(0)

        composeTestRule.setContent {
            key(recomposeTrigger.value) {
                HomeScreen(
                    state = viewModel.state.collectAsState().value,
                    onAction = viewModel::onAction,
                    profilePainter = ColorPainter(Color.Gray),
                    isTest = true
                )
            }
        }

        // Initial state: not selected.
        composeTestRule
            .onNodeWithTag("bookmark_icon_${recipe1.id}")
            .assertIsNotSelected()

        // Click to toggle bookmark.
        composeTestRule
            .onNodeWithTag("bookmark_icon_${recipe1.id}")
            .performClick()

        // ViewModel state updated.
        composeTestRule.runOnIdle {
            assertThat(viewModel.state.value.bookmarkedRecipeIds)
                .contains(recipe1.id)
        }

        // UI state updated.
        composeTestRule
            .onNodeWithTag("bookmark_icon_${recipe1.id}")
            .assertIsSelected()

        // Simulate rotation/recomposition by forcing a recomposition.
        composeTestRule.runOnUiThread {
            recomposeTrigger.value++
        }
        composeTestRule.waitForIdle()

        // Bookmark state should persist after recomposition.
        composeTestRule
            .onNodeWithTag("bookmark_icon_${recipe1.id}")
            .assertIsSelected()
    }

    @Test
    fun loadHome_success_showsContent_andNoError() {
        viewModel.onAction(HomeAction.LoadHome)

        composeTestRule.setContent {
            HomeScreen(
                state = viewModel.state.collectAsState().value,
                onAction = viewModel::onAction,
                profilePainter = ColorPainter(Color.Gray),
                isTest = true
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule
            .onAllNodesWithTag("home_error")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithTag("bookmark_icon_${recipe1.id}")
            .assertCountEquals(1)
    }

    @Test
    fun loadHome_error_showsErrorUI_andHidesContent() {
        fakeRepository.shouldThrowOnGetRecipes = true
        viewModel.onAction(HomeAction.LoadHome)

        composeTestRule.setContent {
            HomeScreen(
                state = viewModel.state.collectAsState().value,
                onAction = viewModel::onAction,
                profilePainter = ColorPainter(Color.Gray),
                isTest = true
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule
            .onAllNodesWithTag("home_error")
            .assertCountEquals(1)

        composeTestRule
            .onAllNodesWithTag("bookmark_icon_${recipe1.id}")
            .assertCountEquals(0)
    }
}
