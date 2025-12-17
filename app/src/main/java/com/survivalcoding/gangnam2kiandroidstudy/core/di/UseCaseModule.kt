package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngridentRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetRecipeDetailUseCase(
        recipeRepository: RecipeRepository,
        procedureRepository: ProcedureRepository,
        ingridentRepository: IngridentRepository
    ): GetRecipeDetailsUseCase {
        return GetRecipeDetailsUseCase(
            recipeRepository = recipeRepository,
            procedureRepository = procedureRepository,
            ingridentRepository = ingridentRepository
        )
    }

    @Provides
    fun provideGetSavedRecipesUseCase(
        recipeRepository: RecipeRepository,
        bookmarkRepository: BookmarkRepository
    ): GetSavedRecipesUseCase {
        return GetSavedRecipesUseCase(
            recipeRepository = recipeRepository,
            bookmarkRepository = bookmarkRepository
        )
    }
}