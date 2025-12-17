package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.DataRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.DataRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRecipeRepository(recipeDataSource: RecipeDataSource): RecipeRepository {
        return RecipeRepositoryImpl(recipeDataSource)
    }

    @Provides
    fun provideDataRepository(): DataRepository {
        return DataRepositoryImpl()
    }
}