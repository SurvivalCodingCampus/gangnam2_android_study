package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSoureImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.UserDto
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngridentRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeDetail
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngridentRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import kotlin.collections.map

class GetRecipeDetailsUseCaseTest {

    val recipeRepository: RecipeRepository = RecipeRepositoryImpl(
        recipeDataSource = MockRecipeDataSourceImpl()
    )
    val ingridentRepository: IngridentRepository = IngridentRepositoryImpl(
        recipeDataSource = MockRecipeDataSourceImpl()
    )
    val procedureRepository: ProcedureRepository = ProcedureRepositoryImpl(
        procedureDataSource = ProcedureDataSoureImpl()
    )
    val useCase = GetRecipeDetailsUseCase(
        recipeRepository = recipeRepository,
        procedureRepository = procedureRepository,
        ingridentRepository = ingridentRepository
    )

    @Test
    fun `execute()시 id 가 같은 데이터 반환 테스트`() = runTest {
        val expected = RecipeDetail(
            recipe = recipeRepository.getRecipes()[0],
            ingredients = ingridentRepository.getIngrident(1),
            procedures = procedureRepository.getProcedure(1)
        )


        TestCase.assertEquals(expected.recipe, useCase.execute(1).recipe)
        TestCase.assertEquals(expected.ingredients, useCase.execute(1).ingredients)
        TestCase.assertEquals(expected.procedures, useCase.execute(1).procedures)
    }

}