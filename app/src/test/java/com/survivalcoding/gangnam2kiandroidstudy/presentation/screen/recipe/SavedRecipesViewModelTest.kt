package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import com.survivalcoding.gangnam2kiandroidstudy.data.Repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SavedRecipesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    // Fake Repository
    private val fakeRepository = mockk<RecipeRepository>()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `초기 상태 - emptyList`() = runTest {
        // given
        coEvery { fakeRepository.getRecipes() } returns emptyList()

        // when
        val viewModel = SavedRecipesViewModel(fakeRepository)

        // then
        assertEquals(emptyList<Recipe>(), viewModel.recipes.value)
    }

    @Test
    fun `init 호출 - 자동으로 레시피 로드`() = runTest {
        // given
        val mockList = listOf(
            Recipe(
                title = "Pizza",
                rating = 4.5,
                imageUrls = "url",
                chef = "chef",
                time = "10 min",
            )
        )
        coEvery { fakeRepository.getRecipes() } returns mockList

        // when
        val viewModel = SavedRecipesViewModel(fakeRepository)
        testDispatcher.scheduler.advanceUntilIdle() // 코루틴 처리

        // then
        assertEquals(mockList, viewModel.recipes.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}