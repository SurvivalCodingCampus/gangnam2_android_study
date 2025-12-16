package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.util.date
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Rule


class SavedRecipesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockUseCase = mockk<GetSavedRecipesUseCase>()

    @Test
    fun `viewModel 테스트`() = runTest {
        // Given
        val recipeList = listOf(
            Recipe(
                title = "Traditional spare ribs baked",
                chef = "Chef John",
                time = "20 min",
                category = "Chinese",
                rating = 4.0,
                imageUrls = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                createdAt = date("2025-12-04"),
                id = 1,
                address = "Seoul"
            ),
            Recipe(
                title = "Spice roasted chicken with flavored rice",
                chef = "Mark Kelvin",
                time = "20 min",
                category = "Chinese",
                rating = 4.0,
                imageUrls = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
                createdAt = date("2025-12-04"),
                id = 2,
                address = "Seoul"
            ),
            Recipe(
                title = "Spicy fried rice mix chicken bali",
                chef = "Spicy Nelly",
                time = "20 min",
                category = "Chinese",
                rating = 4.0,
                imageUrls = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
                createdAt = date("2025-12-04"),
                id = 3,
                address = "Seoul"
            ),
            Recipe(
                title = "Ttekbokki",
                chef = "Kim Dahee",
                time = "30 min",
                category = "Chinese",
                rating = 5.0,
                imageUrls = "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
                createdAt = date("2025-12-04"),
                id = 4,
                address = "Seoul"
            )
        )


        coEvery { mockUseCase.execute(0) } returns recipeList

        val viewModel = SavedRecipesViewModel(mockUseCase)


        // When
        viewModel.getRecipes(0)

        advanceUntilIdle()

        // Then
        val recipes = viewModel.state.value
        assertEquals(4, recipes.recipes.size)
        assertEquals(recipeList[0].title, recipes.recipes[0].title)
        assertEquals(recipeList[1].title, recipes.recipes[1].title)
        assertEquals(recipeList[2].title, recipes.recipes[2].title)
        assertEquals(recipeList[3].title, recipes.recipes[3].title)
    }

}