package com.survivalcoding.gangnam2kiandroidstudy.di

import android.content.Context
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppAssetManagerImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedRecipes.SavedRecipesViewModel

// 임의의 싱글톤 DI 컨테이너
object DependencyContainer {
    fun provideRecipeDataSource(context: Context): RecipeDataSource {
        return RecipeDataSourceImpl.getInstance(
            provideAssetManager(context)
        )
    }

    fun provideRecipeRepository(context: Context): RecipeRepository {
        return RecipeRepositoryImpl.getInstance(
            provideRecipeDataSource(context)
        )
    }

    fun provideAssetManager(context: Context) =
        AppAssetManagerImpl(context.assets)

    fun provideSavedRecipesViewModelFactory(context: Context) = viewModelFactory {
        initializer {
            SavedRecipesViewModel(
                recipeRepository = provideRecipeRepository(context)
            )
        }
    }
}