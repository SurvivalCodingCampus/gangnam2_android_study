package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RemoteRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockIngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProfileRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProfileRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase

class AppApplication : Application() {

    val recipeDataSource: RecipeDataSource by lazy { RemoteRecipeDataSourceImpl() }

    val recipeRepository: RecipeRepository by lazy { RecipeRepositoryImpl(recipeDataSource) }
    val bookmarkRepository: BookmarkRepository by lazy { MockBookmarkRepositoryImpl }
    val profileRepository: ProfileRepository by lazy { MockProfileRepositoryImpl }
    val ingredientRepository: IngredientRepository by lazy { MockIngredientRepositoryImpl }
    val procedureRepository: ProcedureRepository by lazy { MockProcedureRepositoryImpl }

    val getSavedRecipesUseCase: GetSavedRecipesUseCase by lazy {
        GetSavedRecipesUseCase(recipeRepository)
    }
    val toggleBookmarkUseCase: ToggleBookmarkUseCase by lazy {
        ToggleBookmarkUseCase(bookmarkRepository)
    }
    val getRecipeDetailsUseCase: GetRecipeDetailsUseCase by lazy {
        GetRecipeDetailsUseCase(
            recipeRepository,
            profileRepository,
            ingredientRepository,
            procedureRepository,
        )
    }
}