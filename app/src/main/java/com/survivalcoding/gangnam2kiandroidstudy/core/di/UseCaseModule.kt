package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProfileRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetSavedRecipesUseCase(
        recipeRepository: RecipeRepository,
    ): GetSavedRecipesUseCase {
        return GetSavedRecipesUseCase(recipeRepository)
    }

    @Provides
    @Singleton
    fun provideToggleBookmarkUseCase(
        bookmarkRepository: BookmarkRepository,
    ): ToggleBookmarkUseCase {
        return ToggleBookmarkUseCase(bookmarkRepository)
    }

    @Provides
    @Singleton
    fun provideGetRecipeDetailsUseCase(
        recipeRepository: RecipeRepository,
        profileRepository: ProfileRepository,
        ingredientRepository: IngredientRepository,
        procedureRepository: ProcedureRepository,
    ): GetRecipeDetailsUseCase {
        return GetRecipeDetailsUseCase(
            recipeRepository,
            profileRepository,
            ingredientRepository,
            procedureRepository,
        )
    }
}