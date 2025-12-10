package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import app.cash.turbine.test
import com.survivalcoding.gangnam2kiandroidstudy.core.DispatcherRule
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.exception.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.temp.MockRecipeData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchRecipeViewModelTest {

    @get:Rule
    val testDispatcher = DispatcherRule()

    private lateinit var repository: RecipeRepository
    private lateinit var viewModel: SearchRecipeViewModel

    private val mockRecipes = MockRecipeData.recipes

    @Before
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun `데이터 요청에 성공하면 Result Success가 응답된다`() = runTest {
        // given
        coEvery {
            repository.search(any(), any(), any(), any())
        } returns Result.Success(mockRecipes)

        // when
        viewModel = SearchRecipeViewModel(repository)

        // then
        viewModel.uiState.test {
            val initial = awaitItem()
            assertEquals(emptyList<Recipe>(), initial.data)

            val updated = awaitItem()
            assertEquals(mockRecipes, updated.data)
        }
    }

    @Test
    fun `데이터 요청에 실패하면 Result Failure가 응답된다`() = runTest {
        // given
        val error = NetworkError.NetworkUnavailable
        coEvery {
            repository.search(any(), any(), any(), any())
        } returns Result.Failure(error)

        // when
        viewModel = SearchRecipeViewModel(repository)

        // then
        viewModel.uiState.test {
            val initial = awaitItem()
            assertEquals(emptyList<Recipe>(), initial.data)

            val updated = awaitItem()
            assertEquals(error.toString(), updated.error)
        }
    }

    @Test
    fun `검색어를 업데이트하면 debounce 이후 검색이 호출된다`() = runTest {
        // given
        val query = "spic"
        val expected = mockRecipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true)
        }

        coEvery {
            repository.search(any(), any(), any(), any())
        } returns Result.Success(expected)

        // when
        viewModel = SearchRecipeViewModel(repository)

        // then
        viewModel.uiState.test {
            skipItems(1)

            viewModel.updateSearch(query)

            advanceUntilIdle()

            val result = awaitItem()
            assertEquals(expected.size, result.data.size)
            assertEquals(expected, result.data)
        }
    }

    @Test
    fun `필터에서 Rate를 변경하면 검색이 다시 호출된다`() = runTest {
        // given
        val rate = 5.0
        val expected = mockRecipes.filter { recipe ->
            recipe.rating >= rate
        }

        coEvery {
            repository.search(any(), any(), any(), any())
        } returns Result.Success(expected)

        // when
        viewModel = SearchRecipeViewModel(repository)

        // then
        viewModel.uiState.test {
            skipItems(2)

            viewModel.updateFilter("All", rate, "All")

            advanceUntilIdle()

            val result = awaitItem()
            assertEquals(expected.size, result.data.size)
            assertEquals(expected, result.data)
        }
    }

    @Test
    fun `필터에서 Category를 변경하면 검색이 다시 호출된다`() = runTest {
        // given
        val category = "french"
        val expected = mockRecipes.filter { recipe ->
            recipe.category.equals(category, ignoreCase = true)
        }

        coEvery {
            repository.search(any(), any(), any(), any())
        } returns Result.Success(expected)

        // when
        viewModel = SearchRecipeViewModel(repository)

        // then
        viewModel.uiState.test {
            skipItems(2)

            viewModel.updateFilter("All", 1.0, category)

            advanceUntilIdle()

            val result = awaitItem()
            assertEquals(expected.size, result.data.size)
            assertEquals(expected, result.data)
        }
    }

    @Test
    fun `검색어와 Rate 필터를 설정하고 검색한다`() = runTest {
        // given
        val query = "french"
        val rate = 3.0
        val expected = mockRecipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true) && recipe.rating >= rate
        }

        coEvery {
            repository.search(any(), any(), any(), any())
        } returns Result.Success(expected)

        // when
        viewModel = SearchRecipeViewModel(repository)

        // then
        viewModel.uiState.test {
            skipItems(2)

            viewModel.updateSearch(query)
            viewModel.updateFilter("All", rate, "All")

            advanceUntilIdle()

            val result = awaitItem()
            assertEquals(expected.size, result.data.size)
            assertEquals(expected, result.data)
        }
    }

    @Test
    fun `검색어와 Category 필터를 설정하고 검색한다`() = runTest {
        // given
        val query = "tt"
        val category = "japanese"
        val expected = mockRecipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true) && recipe.category.equals(category, ignoreCase = true)
        }

        coEvery {
            repository.search(any(), any(), any(), any())
        } returns Result.Success(expected)

        // when
        viewModel = SearchRecipeViewModel(repository)

        // then
        viewModel.uiState.test {
            skipItems(2)

            viewModel.updateSearch(query)
            viewModel.updateFilter("All", 1.0, category)

            advanceUntilIdle()

            val result = awaitItem()
            assertEquals(expected.size, result.data.size)
            assertEquals(expected, result.data)
        }
    }

    @Test
    fun `검색어와 Rate & Category 필터를 설정하고 검색한다`() = runTest {
        // given
        val query = "spic"
        val rate = 3.0
        val category = "asian"
        val expected = mockRecipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true)
                    && recipe.rating >= rate
                    && recipe.category.equals(category, ignoreCase = true)
        }

        coEvery {
            repository.search(any(), any(), any(), any())
        } returns Result.Success(expected)

        // when
        viewModel = SearchRecipeViewModel(repository)

        // then
        viewModel.uiState.test {
            skipItems(2)

            viewModel.updateSearch(query)
            viewModel.updateFilter("All", rate, category)

            advanceUntilIdle()

            val result = awaitItem()
            assertEquals(expected.size, result.data.size)
            assertEquals(expected, result.data)
        }
    }
}