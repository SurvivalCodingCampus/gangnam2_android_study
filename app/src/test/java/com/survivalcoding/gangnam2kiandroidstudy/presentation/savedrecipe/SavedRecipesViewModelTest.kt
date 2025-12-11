package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.util.date
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals


class SavedRecipesViewModelTest {

    @Test
    fun `viewModel이 데이터를 잘 가져오는지 테스트`() = runTest {
        // Given
        val recipeList = listOf(
            Recipe(
                title = "Traditional spare ribs baked",
                chef = "Chef John",
                time = "20 min",
                category = "Chinese",
                rating = 4.0,
                imageUrls = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                createdAt = date("2025-12-04")
            ),
            Recipe(
                title = "Spice roasted chicken with flavored rice",
                chef = "Mark Kelvin",
                time = "20 min",
                category = "Chinese",
                rating = 4.0,
                imageUrls = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
                createdAt = date("2025-12-04")
            ),
            Recipe(
                title = "Spicy fried rice mix chicken bali",
                chef = "Spicy Nelly",
                time = "20 min",
                category = "Chinese",
                rating = 4.0,
                imageUrls = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
                createdAt = date("2025-12-04")
            ),
            Recipe(
                title = "Ttekbokki",
                chef = "Kim Dahee",
                time = "30 min",
                category = "Chinese",
                rating = 5.0,
                imageUrls = "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
                createdAt = date("2025-12-04")
            )
        )
        val mockRepository = object : RecipeRepository {
            override suspend fun getRecipes(): List<Recipe> {
                return recipeList
            }
        }
        val viewModel = SavedRecipesViewModel(mockRepository)

        // When
        viewModel.getRecipes()

        // Then
        val recipes = viewModel.recipes.value
        assertEquals(4, recipes.size)
        assertEquals(recipeList[0].title, recipes[0].title)
        assertEquals(recipeList[1].title, recipes[1].title)
        assertEquals(recipeList[2].title, recipes[2].title)
        assertEquals(recipeList[3].title, recipes[3].title)
    }

}