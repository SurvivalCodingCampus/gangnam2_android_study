package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import app.cash.turbine.test
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SavedRecipeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var repository: RecipeRepository
    private lateinit var viewModel: SavedRecipeViewModel

    val recipes = MockRecipeData.recipeListThree

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

//    @Test
//    fun `데이터 요청에 성공하면 Result Success가 응답된다`() = testScope.runTest {
//        // given
//        coEvery { repository.findAll() } returns Result.Success(recipes)
//
//        viewModel = SavedRecipeViewModel(repository)
//
//        // then
//        viewModel.uiState.test {
//            val state = awaitItem()
//            assertEquals(emptyList<Recipe>(), state.data)
//
//            val updated = awaitItem()
//            assertEquals(recipes, updated.data)
//        }
//    }
//
//    @Test
//    fun `데이터 요청에 실패하면 Result Failure가 응답된다`() = testScope.runTest {
//        // given
//        val error = NetworkError.NetworkUnavailable
//        coEvery { repository.findAll() } returns Result.Failure(error)
//
//        viewModel = SavedRecipeViewModel(repository)
//
//        // then
//        viewModel.uiState.test {
//            val state = awaitItem()
//            assertEquals(emptyList<Recipe>(), state.data)
//
//            val updated = awaitItem()
//            assertEquals(error.toString(), updated.error)
//        }
//    }
}