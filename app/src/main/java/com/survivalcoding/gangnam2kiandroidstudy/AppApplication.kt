package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase

class AppApplication : Application() {

    // DataSource
    private val recipeDataSource by lazy {
        MockRecipeDataSourceImpl()
    }

    // Repository
    val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(recipeDataSource)
    }

    val bookmarkRepository: BookmarkRepository by lazy {
        BookmarkRepositoryImpl()
    }

    // UseCase
    val getSavedRecipesUseCase: GetSavedRecipesUseCase by lazy {
        GetSavedRecipesUseCase(
            bookmarkRepository = bookmarkRepository,
            recipeRepository = recipeRepository
        )
    }

    val toggleBookmarkUseCase: ToggleBookmarkUseCase by lazy {
        ToggleBookmarkUseCase(
            bookmarkRepository = bookmarkRepository
        )
    }
}