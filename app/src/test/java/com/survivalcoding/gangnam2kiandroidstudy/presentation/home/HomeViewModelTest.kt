package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.util.date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val recipeList = listOf(
        Recipe(
            title = "Traditional spare ribs baked",
            chef = "Chef John",
            time = "20 min",
            category = "Italian",
            rating = 4.0,
            imageUrls = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            createdAt = date("2025-12-10")
        ),
        Recipe(
            title = "Spice roasted chicken with flavored rice",
            chef = "Mark Kelvin",
            time = "20 min",
            category = "Chinese",
            rating = 4.0,
            imageUrls = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            createdAt = date("2025-10-10")
        ),
        Recipe(
            title = "Spicy fried rice mix chicken bali",
            chef = "Spicy Nelly",
            time = "20 min",
            category = "Italian",
            rating = 4.0,
            imageUrls = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
            createdAt = date("2025-12-04")
        ),
        Recipe(
            title = "Ttekbokki",
            chef = "Kim Dahee",
            time = "30 min",
            category = "Asian",
            rating = 5.0,
            imageUrls = "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
            createdAt = date("2020-12-10")
        )
    )

    private val mockRepository = object : RecipeRepository {
        override suspend fun getRecipes(): List<Recipe> {
            return recipeList
        }
    }

    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(mockRepository)
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
    fun `viewModel 카테고리 필터링 테스트()- Italian`() = runTest {
        val filter = "Italian"
        viewModel.onSelectCategory(filter)

        val filteredRecipes = viewModel.state.value.filteredRecipes
        assertEquals(2, filteredRecipes.size)
        assertEquals("Traditional spare ribs baked", filteredRecipes[0].title)
        assertEquals("Spicy fried rice mix chicken bali", filteredRecipes[1].title)
    }

    @Test
    fun `viewModel 카테고리 필터링 테스트()- Asian`() = runTest {
        val filter = "Asian"
        viewModel.onSelectCategory(filter)

        val filteredRecipes = viewModel.state.value.filteredRecipes
        assertEquals(1, filteredRecipes.size)
        assertEquals("Ttekbokki", filteredRecipes[0].title)
    }
    @Test
    fun `viewModel 카테고리 필터링 테스트()- All`() = runTest {
        val filter = "All"
        viewModel.onSelectCategory(filter)

        val filteredRecipes = viewModel.state.value.filteredRecipes
        assertEquals(4, filteredRecipes.size)
        assertEquals("Traditional spare ribs baked", filteredRecipes[0].title)
        assertEquals("Spice roasted chicken with flavored rice", filteredRecipes[1].title)
        assertEquals("Spicy fried rice mix chicken bali", filteredRecipes[2].title)
        assertEquals("Ttekbokki", filteredRecipes[3].title)
    }

}