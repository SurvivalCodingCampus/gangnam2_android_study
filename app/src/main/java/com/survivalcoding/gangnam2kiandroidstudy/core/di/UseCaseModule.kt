package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetNewRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase
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
    fun provideGetRecipeDetailUseCase(
        recipeRepository: RecipeRepository,
        ingredientRepository: IngredientRepository,
        chefRepository: ChefRepository,
        procedureRepository: ProcedureRepository,
    ): GetRecipeDetailUseCase {
        return GetRecipeDetailUseCase(
            recipeRepository,
            ingredientRepository,
            chefRepository,
            procedureRepository
        )

    }

    @Provides
    @Singleton
    fun provideGetSavedRecipeUseCase(
        bookmarkRepository: BookmarkRepository,
        recipeRepository: RecipeRepository
    ): GetSavedRecipesUseCase {
        return GetSavedRecipesUseCase(
            bookmarkRepository,
            recipeRepository
        )
    }

    @Provides
    @Singleton
    fun provideToggleBookmarkUseCase(
        bookmarkRepository: BookmarkRepository
    ): ToggleBookmarkUseCase {
        return ToggleBookmarkUseCase(
            bookmarkRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetNewRecipesUseCase(
        recipeRepository: RecipeRepository,
        chefRepository: ChefRepository
    ): GetNewRecipesUseCase {
        return GetNewRecipesUseCase(
            recipeRepository,
            chefRepository
        )
    }
}