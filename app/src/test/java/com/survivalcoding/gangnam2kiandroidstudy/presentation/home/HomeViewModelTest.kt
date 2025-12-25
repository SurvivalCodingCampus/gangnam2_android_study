package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.HomeRecipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetHomeRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
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
    private val getHomeRecipesUseCase: GetHomeRecipesUseCase = mockk()
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase = mockk()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init loads recipes successfully and updates state`() = runTest {
        // Given
        val mockRecipes = listOf(
            HomeRecipe(
                recipe = Recipe(
                    id = 1,
                    category = "Korean",
                    name = "Kimchi",
                    image = "",
                    chef = "Chef",
                    time = "10m",
                    rating = 5.0,
                    ingredients = emptyList()
                ),
                chefImageUrl = "",
                isBookmarked = false
            )
        )
        coEvery { getHomeRecipesUseCase.execute() } returns mockRecipes

        // When
        viewModel = HomeViewModel(getHomeRecipesUseCase, toggleBookmarkUseCase)
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        assertEquals(mockRecipes, state.homeRecipes)
        assertEquals(listOf("All", "Korean"), state.categories)
        assertEquals(false, state.isLoading)
    }

    @Test
    fun `init emits ShowError event when loading fails`() = runTest {
        // Given
        val errorMessage = "Network Error"
        coEvery { getHomeRecipesUseCase.execute() } throws RuntimeException(errorMessage)

        // When
        viewModel = HomeViewModel(getHomeRecipesUseCase, toggleBookmarkUseCase)
        
        // Collect events
        val events = mutableListOf<HomeEvent>()
        val job = launch {
            viewModel.event.collect { events.add(it) }
        }

        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(events.any { it is HomeEvent.ShowError && it.message.contains(errorMessage) })
        assertEquals(false, viewModel.state.value.isLoading)
        job.cancel()
    }
}
