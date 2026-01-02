package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.core.MainDispatcherRule
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.UserRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RecipeHomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    private lateinit var mockRepository: RecipeRepository
    private lateinit var mockUserRepository: UserRepository
    private lateinit var viewModel: RecipeHomeViewModel

    private val dummyRecipes = MockRecipeData.recipeListThree

    @Before
    fun setUp() = runTest(testDispatcher) {
        mockRepository = mockk()
        mockUserRepository = mockk()

        coEvery {
            mockRepository.search(
                query = "",
                time = "All",
                rate = 1.0,
                category = "All"
            )
        } returns Result.Success(dummyRecipes)

        coEvery { mockUserRepository.loadById(1) } returns flowOf(null)

        viewModel = RecipeHomeViewModel(mockRepository, mockUserRepository)
    }

    @Test
    fun `초기화 시 데이터가 조회된다`() = runTest(testDispatcher) {
        coVerify(exactly = 1) {
            mockRepository.search(
                query = "",
                time = "All",
                rate = 1.0,
                category = RecipeCategory.ALL.displayName
            )
        }

        val state = viewModel.state.value
        assertEquals(dummyRecipes, state.recipes)
    }

}