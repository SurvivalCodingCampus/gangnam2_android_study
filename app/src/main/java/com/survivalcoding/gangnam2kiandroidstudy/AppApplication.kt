package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockChefDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockIngredientDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeIngredientDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ChefRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase

class AppApplication : Application() {

    // DataSource
    private val recipeDataSource by lazy {
        MockRecipeDataSourceImpl()
    }

    private val ingredientDataSource by lazy {
        MockIngredientDataSourceImpl()
    }

    private val recipeIngredientDataSource by lazy {
        MockRecipeIngredientDataSourceImpl()
    }

    private val chefDataSource by lazy {
        MockChefDataSourceImpl()
    }


    // Repository

    val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(recipeDataSource)
    }

    val ingredientRepository: IngredientRepository by lazy {
        IngredientRepositoryImpl(
            ingredientDataSource = ingredientDataSource,
            recipeIngredientDataSource = recipeIngredientDataSource
        )
    }

    val chefRepository: ChefRepository by lazy {
        ChefRepositoryImpl(chefDataSource)
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