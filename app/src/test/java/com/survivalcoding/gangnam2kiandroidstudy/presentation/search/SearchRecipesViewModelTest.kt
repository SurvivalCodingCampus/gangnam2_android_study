package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.util.date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class SearchRecipesViewModelTest {

    private val recipeList = listOf(
        Recipe(
            title = "Traditional spare ribs baked",
            chef = "Chef John",
            time = "20 min",
            category = "Chinese",
            rating = 4.0,
            imageUrls = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            createdAt = date("2025-12-10"),
            id = 0,
            address = "Seoul"
        ),
        Recipe(
            title = "Spicy roasted chicken with flavored rice",
            chef = "Mark Kelvin",
            time = "20 min",
            category = "Chinese",
            rating = 4.0,
            imageUrls = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            createdAt = date("2025-10-10"),
            id = 1,
            address = "Seoul"
        ),
        Recipe(
            title = "Spicy fried rice mix chicken bali",
            chef = "Spicy Nelly",
            time = "20 min",
            category = "Italian",
            rating = 4.0,
            imageUrls = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
            createdAt = date("2025-12-04"),
            id = 2,
            address = "Seoul"
        ),
        Recipe(
            title = "Ttekbokki",
            chef = "Kim Dahee",
            time = "30 min",
            category = "Korean",
            rating = 5.0,
            imageUrls = "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
            createdAt = date("2020-12-10"),
            id = 3,
            address = "Seoul"
        )
    )

    private val mockRepository = object : RecipeRepository {
        override suspend fun getRecipes(): List<Recipe> {
            return recipeList
        }
    }

    private lateinit var viewModel: SearchRecipesViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchRecipesViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `viewModel이 데이터를 잘 가져오는지 테스트`() = runTest {
        // When
        viewModel.getAllRecipes()
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        val recipes = viewModel.state.value.recipes
        assertEquals(4, recipes.size)
        assertEquals(recipeList[0].title, recipes[0].title)
        assertEquals(recipeList[1].title, recipes[1].title)
        assertEquals(recipeList[2].title, recipes[2].title)
        assertEquals(recipeList[3].title, recipes[3].title)
    }

    @Test
    fun `검색어 입력 시 레시피가 필터링된다`() = runTest {
        // Given
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        viewModel.onAction(
            SearchRecipesAction.OnSearchRecipes("Spicy")
        )

        advanceTimeBy(300)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(2, state.filteredRecipes.size)
        assertEquals("Spicy roasted chicken with flavored rice", state.filteredRecipes[0].title)
        assertEquals("Spicy fried rice mix chicken bali", state.filteredRecipes[1].title)
    }

    @Test
    fun `필터 버튼 클릭 시 bottomSheet 상태가 바뀐다`() = runTest {
        // Given
        val expected = viewModel.state.value.showBottomSheet

        // When
        viewModel.onAction(SearchRecipesAction.OnTapFilterButton)

        // Then
        assertEquals(!expected, viewModel.state.value.showBottomSheet)
    }

    @Test
    fun `카테고리 필터 적용 시 해당 카테고리만 필터링된다`() = runTest {
        // Given
        testDispatcher.scheduler.advanceUntilIdle()

        val filterState = FilterSearchState(
            selectedCategoryText = "Chinese"
        )

        // When
        viewModel.onAction(
            SearchRecipesAction.OnUpdateFilterSearchState(filterState)
        )
        advanceUntilIdle()

        // Then
        val result = viewModel.state.value.filteredRecipes
        assertEquals(2, result.size)
        assertEquals(true, result.all { it.category == "Chinese" })
    }


}