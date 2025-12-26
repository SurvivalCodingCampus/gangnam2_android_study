package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.HomeImage
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetNewRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val recipeRepository: RecipeRepository = mockk()
    private val bookmarkRepository: BookmarkRepository = mockk()
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase = mockk()
    private val getNewRecipesUseCase: GetNewRecipesUseCase = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val dummyRecipe = Recipe(
        id = 1,
        title = "Test Recipe",
        chefId = 1,
        chefName = "Chef",
        time = "10 min",
        rating = 5.0,
        imageUrls = "",
        createdAt = 0L,
        category = "Cereal",
        homeImage = HomeImage.FOOD1
    )

    private fun createViewModel(): HomeViewModel {
        return HomeViewModel(
            recipeRepository,
            bookmarkRepository,
            toggleBookmarkUseCase,
            getNewRecipesUseCase
        )
    }

    @Test
    fun `init loads data and sets bookmarked ids`() = runTest(testDispatcher) {
        // Given
        coEvery { recipeRepository.getRecipes() } returns listOf(dummyRecipe)
        coEvery { bookmarkRepository.getSavedRecipeIds() } returns listOf(1)
        coEvery { getNewRecipesUseCase.execute() } returns emptyList()

        // When
        viewModel = createViewModel()
        testScheduler.advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        assertTrue(state.bookmarkedIds.contains(1))
        assertEquals(1, state.recipes.size)
    }

    @Test
    fun `ToggleBookmark action calls usecase and updates state to true`() = runTest(testDispatcher) {
        // Given
        coEvery { recipeRepository.getRecipes() } returns listOf(dummyRecipe)
        coEvery { bookmarkRepository.getSavedRecipeIds() } returns emptyList()
        coEvery { getNewRecipesUseCase.execute() } returns emptyList()
        coEvery { toggleBookmarkUseCase.execute(1) } returns Result.Success(true) // Bookmarked

        viewModel = createViewModel()
        testScheduler.advanceUntilIdle()

        // When
        viewModel.onAction(HomeAction.ToggleBookmark(1))
        testScheduler.advanceUntilIdle()

        // Then
        coVerify { toggleBookmarkUseCase.execute(1) }
        assertTrue(viewModel.state.value.bookmarkedIds.contains(1))
    }

    @Test
    fun `ToggleBookmark action calls usecase and updates state to false`() = runTest(testDispatcher) {
        // Given
        coEvery { recipeRepository.getRecipes() } returns listOf(dummyRecipe)
        coEvery { bookmarkRepository.getSavedRecipeIds() } returns listOf(1) // Already bookmarked
        coEvery { getNewRecipesUseCase.execute() } returns emptyList()
        coEvery { toggleBookmarkUseCase.execute(1) } returns Result.Success(false) // Unbookmarked

        viewModel = createViewModel()
        testScheduler.advanceUntilIdle()

        // When
        viewModel.onAction(HomeAction.ToggleBookmark(1))
        testScheduler.advanceUntilIdle()

        // Then
        coVerify { toggleBookmarkUseCase.execute(1) }
        assertTrue(viewModel.state.value.bookmarkedIds.isEmpty())
    }
}
