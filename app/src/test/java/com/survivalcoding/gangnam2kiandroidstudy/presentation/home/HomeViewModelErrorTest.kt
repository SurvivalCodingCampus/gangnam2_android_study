package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelErrorTest {

    private val testDispatcher = StandardTestDispatcher()
    
    private val bookmarkRepository = object : BookmarkRepository {
        override suspend fun getBookmarkId(): List<Int> = emptyList()
        override suspend fun addBookmarkId(id: Int): List<Int> = emptyList()
        override suspend fun removeBookmarkId(id: Int): List<Int> = emptyList()
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `getAllRecipes success updates state with recipes and null error`() = runTest {
        val recipes = listOf(
            Recipe(1, "Title", "Chef", "10m", "Asian", 5.0, "", 0L, "")
        )
        val successRepository = object : RecipeRepository {
            override suspend fun getRecipes(): List<Recipe> = recipes
        }

        val viewModel = HomeViewModel(successRepository, bookmarkRepository)
        
        advanceUntilIdle() // Wait for init block to complete

        assertEquals(recipes, viewModel.state.value.recipes)
        assertNull(viewModel.state.value.errorMessage)
    }

    @Test
    fun `getAllRecipes failure updates state with error message`() = runTest {
        val failureRepository = object : RecipeRepository {
            override suspend fun getRecipes(): List<Recipe> = throw Exception("Network Error")
        }

        val viewModel = HomeViewModel(failureRepository, bookmarkRepository)

        advanceUntilIdle() // Wait for init block to complete

        assertEquals("Failed to load recipes", viewModel.state.value.errorMessage)
        assertEquals(0, viewModel.state.value.recipes.size)
    }
}
