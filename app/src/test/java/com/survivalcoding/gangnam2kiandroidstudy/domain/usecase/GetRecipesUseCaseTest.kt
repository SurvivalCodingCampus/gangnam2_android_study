package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRecipesUseCaseTest {

    private lateinit var repository: RecipeRepository
    private lateinit var useCase: GetRecipesUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetRecipesUseCase(repository)
    }

    @Test
    fun `Result Success로 데이터를 정상적으로 반환한다`() = runTest {
        // given
        val recipes = MockRecipeData.recipeListThree

        coEvery { repository.getRecipes() } returns recipes

        // when
        val result = useCase.invoke()

        // then
        assertTrue(result is Result.Success)
        assertEquals(recipes, (result as Result.Success).data)
    }

    @Test
    fun `Result Failure로 NetworkError를 반환한다`() = runTest {
        // given
        val error = NetworkError.Unknown("네트워크 에러")
        coEvery { repository.getRecipes() } throws error

        // when
        val result = useCase()

        // then
        assertTrue(result is Result.Failure)
        assertEquals(error, (result as Result.Failure).error)
    }

}
