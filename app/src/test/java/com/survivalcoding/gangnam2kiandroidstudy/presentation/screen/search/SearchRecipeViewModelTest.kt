package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.exception.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.temp.MockRecipeData
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchRecipeViewModelTest {

    private lateinit var viewModel: SearchRecipeViewModel
    private lateinit var recipeRepository: RecipeRepository
    private val testDispatcher = StandardTestDispatcher()

    private val mockRecipes = MockRecipeData.recipes

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        recipeRepository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `초기 상태 확인`() = runTest {
        // given
        coEvery { recipeRepository.findAll() } returns Result.Success(mockRecipes)

        // when
        viewModel = SearchRecipeViewModel(recipeRepository)
        advanceUntilIdle()

        // then
        val state = viewModel.uiState.value

        assertEquals("", state.query)
        assertEquals(mockRecipes, state.recipes)
    }

    @Test
    fun `fetchRecipes 성공 시 레시피 목록 로드`() = runTest {
        // given
        coEvery { recipeRepository.findAll() } returns Result.Success(mockRecipes)

        // when
        viewModel = SearchRecipeViewModel(recipeRepository)
        advanceUntilIdle()

        // then
        val state = viewModel.uiState.value

        assertEquals(mockRecipes, state.recipes)
        assertFalse(state.isLoading)

        coVerify { recipeRepository.findAll() }
    }

    @Test
    fun `fetchRecipes 실패 시 에러 처리`() = runTest {
        // given
        val error = NetworkError.NetworkUnavailable
        coEvery { recipeRepository.findAll() } returns Result.Failure(error)

        // when
        viewModel = SearchRecipeViewModel(recipeRepository)
        advanceUntilIdle()

        // then
        val state = viewModel.uiState.value

        assertEquals(error.toString(), state.error)
    }

//    @Test
//    fun `데이터 요청에 성공하면 Result Success가 응답된다`() = runTest {
//        // given
//        coEvery {
//            repository.search(any(), any(), any(), any())
//        } returns Result.Success(mockRecipes)
//
//        // when
//        viewModel = SearchRecipeViewModel(repository)
//
//        // then
//        viewModel.uiState.test {
//            val initial = awaitItem()
//            assertEquals(emptyList<Recipe>(), initial.recipes)
//
//            val updated = awaitItem()
//            assertEquals(mockRecipes, updated.recipes)
//        }
//    }
//
//    @Test
//    fun `데이터 요청에 실패하면 Result Failure가 응답된다`() = runTest {
//        // given
//        val error = NetworkError.NetworkUnavailable
//        coEvery {
//            repository.search(any(), any(), any(), any())
//        } returns Result.Failure(error)
//
//        // when
//        viewModel = SearchRecipeViewModel(repository)
//
//        // then
//        viewModel.uiState.test {
//            val initial = awaitItem()
//            assertEquals(emptyList<Recipe>(), initial.recipes)
//
//            val updated = awaitItem()
//            assertEquals(error.toString(), updated.error)
//        }
//    }
//
//    @Test
//    fun `검색어를 업데이트하면 debounce 이후 검색이 호출된다`() = runTest {
//        // given
//        val query = "spic"
//        val expected = mockRecipes.filter { recipe ->
//            recipe.name.contains(query, ignoreCase = true)
//        }
//
//        coEvery {
//            repository.search(any(), any(), any(), any())
//        } returns Result.Success(expected)
//
//        coEvery { repository.findAll() } returns Result.Success(mockRecipes)
//
//        // when
//        viewModel = SearchRecipeViewModel(repository)
//        runCurrent()
//        advanceUntilIdle()
//
//        viewModel.updateSearch(query)
//        runCurrent()
//
//        advanceTimeBy(1100)
//        runCurrent()
//        advanceUntilIdle()
//
//        // then
//        val result = viewModel.uiState.value
//
//        coVerify(exactly = 1) { repository.search(any(), any(), any(), any()) }
//        assertEquals(expected.size, result.filterRecipes.size)
//        assertEquals(expected, result.filterRecipes)
//    }
//
//    @Test
//    fun `필터에서 Rate를 변경하면 검색이 다시 호출된다`() = runTest {
//        // given
//        val rate = 5.0
//        val expected = mockRecipes.filter { recipe ->
//            recipe.rating >= rate
//        }
//
//        coEvery {
//            repository.search(any(), any(), any(), any())
//        } returns Result.Success(expected)
//
//        // when
//        viewModel = SearchRecipeViewModel(repository)
//
//        // then
//        viewModel.uiState.test {
//            skipItems(2)
//
//            viewModel.updateFilterSearchState(FilterSearchState("All", rate, RecipeCategory.ALL))
//
//            advanceUntilIdle()
//
//            val result = awaitItem()
//            assertEquals(expected.size, result.recipes.size)
//            assertEquals(expected, result.recipes)
//        }
//    }
//
//    @Test
//    fun `필터에서 Category를 변경하면 검색이 다시 호출된다`() = runTest {
//        // given
//        val category = "french"
//        val expected = mockRecipes.filter { recipe ->
//            recipe.category.equals(category, ignoreCase = true)
//        }
//
//        coEvery {
//            repository.search(any(), any(), any(), any())
//        } returns Result.Success(expected)
//
//        // when
//        viewModel = SearchRecipeViewModel(repository)
//
//        // then
//        viewModel.uiState.test {
//            skipItems(2)
//
//            viewModel.updateFilterSearchState(
//                FilterSearchState(
//                    "All", 1.0, RecipeCategory.getRecipeCategoryByDisplayName(category)
//                )
//            )
//
//            advanceUntilIdle()
//
//            val result = awaitItem()
//            assertEquals(expected.size, result.recipes.size)
//            assertEquals(expected, result.recipes)
//        }
//    }
//
//    @Test
//    fun `검색어와 Rate 필터를 설정하고 검색한다`() = runTest {
//        // given
//        val query = "french"
//        val rate = 3.0
//        val expected = mockRecipes.filter { recipe ->
//            recipe.name.contains(query, ignoreCase = true) && recipe.rating >= rate
//        }
//
//        coEvery {
//            repository.search(any(), any(), any(), any())
//        } returns Result.Success(expected)
//
//        // when
//        viewModel = SearchRecipeViewModel(repository)
//
//        // then
//        viewModel.uiState.test {
//            skipItems(2)
//
//            viewModel.updateSearch(query)
//            viewModel.updateFilterSearchState(FilterSearchState("All", rate, RecipeCategory.ALL))
//
//            advanceUntilIdle()
//
//            val result = awaitItem()
//            assertEquals(expected.size, result.recipes.size)
//            assertEquals(expected, result.recipes)
//        }
//    }
//
//    @Test
//    fun `검색어와 Category 필터를 설정하고 검색한다`() = runTest {
//        // given
//        val query = "tt"
//        val category = "japanese"
//        val expected = mockRecipes.filter { recipe ->
//            recipe.name.contains(query, ignoreCase = true) && recipe.category.equals(category, ignoreCase = true)
//        }
//
//        coEvery {
//            repository.search(any(), any(), any(), any())
//        } returns Result.Success(expected)
//
//        // when
//        viewModel = SearchRecipeViewModel(repository)
//
//        // then
//        viewModel.uiState.test {
//            skipItems(2)
//
//            viewModel.updateSearch(query)
//            viewModel.updateFilterSearchState(
//                FilterSearchState(
//                    "All",
//                    1.0,
//                    RecipeCategory.getRecipeCategoryByDisplayName(category)
//                )
//            )
//
//            advanceUntilIdle()
//
//            val result = awaitItem()
//            assertEquals(expected.size, result.recipes.size)
//            assertEquals(expected, result.recipes)
//        }
//    }
//
//    @Test
//    fun `검색어와 Rate & Category 필터를 설정하고 검색한다`() = runTest {
//        // given
//        val query = "spic"
//        val rate = 3.0
//        val category = "asian"
//        val expected = mockRecipes.filter { recipe ->
//            recipe.name.contains(query, ignoreCase = true)
//                    && recipe.rating >= rate
//                    && recipe.category.equals(category, ignoreCase = true)
//        }
//
//        coEvery {
//            repository.search(any(), any(), any(), any())
//        } returns Result.Success(expected)
//
//        // when
//        viewModel = SearchRecipeViewModel(repository)
//
//        // then
//        viewModel.uiState.test {
//            skipItems(2)
//
//            viewModel.updateSearch(query)
//            viewModel.updateFilterSearchState(
//                FilterSearchState(
//                    "All",
//                    rate,
//                    RecipeCategory.getRecipeCategoryByDisplayName(category)
//                )
//            )
//
//            advanceUntilIdle()
//
//            val result = awaitItem()
//            assertEquals(expected.size, result.recipes.size)
//            assertEquals(expected, result.recipes)
//        }
//    }
}