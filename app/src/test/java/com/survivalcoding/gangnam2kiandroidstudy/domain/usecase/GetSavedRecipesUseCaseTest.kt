package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockUserDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.UserDto
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test


class GetSavedRecipesUseCaseTest {
    val recipeRepository: RecipeRepository = RecipeRepositoryImpl(
        recipeDataSource = MockRecipeDataSourceImpl()
    )
    val bookmarkRepository: BookmarkRepository = BookmarkRepositoryImpl(
        userDataSource = MockUserDataSourceImpl()
    )
    val useCase = GetSavedRecipesUseCase(
        recipeRepository = recipeRepository,
        bookmarkRepository = bookmarkRepository
    )


    @Test
    fun `execute()시 id 가 삭제된 List 반환 테스트`() = runTest {
        val expected = UserDto(
            userId = 1,
            name = "John Doe",
            savedRecipesId = listOf(2, 3, 4, 5, 6, 7, 8, 9, 10)
        )


        assertEquals(expected.savedRecipesId, useCase.execute(1).map { it.id })
    }
}