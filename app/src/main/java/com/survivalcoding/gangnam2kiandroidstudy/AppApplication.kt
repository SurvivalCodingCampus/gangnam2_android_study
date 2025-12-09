package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl

class AppApplication : Application() {
    val recipeDataSource by lazy { RecipeDataSourceImpl() }
    val recipeRepository by lazy { RecipeRepositoryImpl(recipeDataSource) }

}