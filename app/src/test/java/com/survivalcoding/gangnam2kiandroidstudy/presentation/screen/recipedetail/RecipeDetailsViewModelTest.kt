@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockIngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProfileRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeDetails
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.bdd.coGiven
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RecipeDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getRecipeDetailsUseCase: GetRecipeDetailsUseCase

    @InjectMockKs
    private lateinit var viewModel: RecipeDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testFetchRecipeDetails() = runTest {
        val mockRecipe = MockRecipeRepositoryImpl.mockRecipes.first()
        val mockProfile = MockProfileRepositoryImpl.mockProfiles.first()
        val mockIngredients = MockIngredientRepositoryImpl.mockIngredients
        val mockProcedures = MockProcedureRepositoryImpl.mockProcedures

        coGiven { getRecipeDetailsUseCase(any()) } returns AppResult.Success(
            RecipeDetails(
                recipe = mockRecipe,
                profile = mockProfile,
                ingredients = mockIngredients,
                procedures = mockProcedures,
            ),
        )

        val recipeId = 1L
        viewModel.fetchRecipeDetails(recipeId)

        assertTrue(viewModel.uiState.value.isLoading)

        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isLoading)

        val recipe = viewModel.uiState.value.recipe
        assertEquals(mockRecipe, recipe)

        val profile = viewModel.uiState.value.profile
        assertEquals(mockProfile, profile)

        val ingredients = viewModel.uiState.value.ingredients
        assertEquals(mockIngredients, ingredients)

        val procedures = viewModel.uiState.value.procedures
        assertEquals(mockProcedures, procedures)
    }
}