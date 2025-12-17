package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.presentation.todo.TodoDataSource
import com.survivalcoding.gangnam2kiandroidstudy.presentation.todo.TodoDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRecipeDataSource(): RecipeDataSource {
        return RecipeDataSourceImpl(
            context = AppApplication(),
        )
    }


    @Provides
    @Singleton
    fun provideTodoDataSource(): TodoDataSource {
        return TodoDataSourceImpl()
    }
}
