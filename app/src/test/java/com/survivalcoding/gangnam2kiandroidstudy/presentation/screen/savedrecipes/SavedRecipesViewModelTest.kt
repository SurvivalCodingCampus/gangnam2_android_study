@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.bdd.coGiven
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SavedRecipesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getSavedRecipesUseCase: GetSavedRecipesUseCase

    @MockK
    private lateinit var toggleBookmarkUseCase: ToggleBookmarkUseCase

    private lateinit var viewModel: SavedRecipesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testSavedRecipesViewModel() = runTest {
        coGiven { getSavedRecipesUseCase() } returns flowOf(
            AppResult.Success(
                listOf(
                    Recipe(
                        id = 1,
                        name = "Test Recipe",
                        imageUrl = "imageUrl",
                        chef = "chef",
                        time = 10,
                        rating = 4.5,
                    ),
                    Recipe(
                        id = 2,
                        name = "Test Recipe2",
                        imageUrl = "imageUrl",
                        chef = "chef",
                        time = 10,
                        rating = 4.5,
                    ),
                ),
            ),
        )

        viewModel = SavedRecipesViewModel(
            getSavedRecipesUseCase,
            toggleBookmarkUseCase,
        )

        advanceUntilIdle()

        val recipes = viewModel.uiState.value.recipes

        assertEquals(2, recipes.size)
        assertEquals("Test Recipe", recipes[0].name)
        assertEquals("Test Recipe2", recipes[1].name)
    }
}