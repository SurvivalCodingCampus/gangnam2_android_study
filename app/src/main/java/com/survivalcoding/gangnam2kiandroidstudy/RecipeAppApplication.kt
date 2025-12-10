package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data_source.SavedRecipesDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.repository.SavedRecipesRepositoryImpl

class RecipeAppApplication : Application() {
    val savedRecipesDataSource by lazy { SavedRecipesDataSourceImpl() }
    val savedRecipesRepository by lazy { SavedRecipesRepositoryImpl(savedRecipesDataSource) }

}