package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.Repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.Repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data_source.MockRecipeDataSourceImpl

class AppApplication : Application() {
    val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(
            recipeDataSource = MockRecipeDataSourceImpl()
        )
    }
}