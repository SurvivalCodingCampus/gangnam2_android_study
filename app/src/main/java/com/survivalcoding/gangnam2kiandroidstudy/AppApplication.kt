package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.Repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.Repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl

class AppApplication : Application() {
    val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(
            recipeDataSource = MockRecipeDataSourceImpl()
        )
    }
}