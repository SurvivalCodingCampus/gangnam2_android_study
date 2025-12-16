package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.DataRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.DataRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.FilterRecipesUseCase

class AppApplication : Application() {

    val filterRecipesUseCase: FilterRecipesUseCase by lazy {
        FilterRecipesUseCase(recipeRepository)
    }

    val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(MockRecipeDataSourceImpl())
    }

    val dataRepository: DataRepository by lazy {
        DataRepositoryImpl()
    }

    override fun onCreate() {
        super.onCreate()

        println("AppApplication created")
    }
}