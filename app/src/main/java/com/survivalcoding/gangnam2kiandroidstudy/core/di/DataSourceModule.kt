package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockUserDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSoureImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideRecipeDataSource(): RecipeDataSource {
        return MockRecipeDataSourceImpl()
    }
    @Provides
    fun provideProcedureDataSource(): ProcedureDataSource {
        return ProcedureDataSoureImpl()
    }
    @Provides
    fun provideUserDataSource(): UserDataSource {
        return MockUserDataSourceImpl()
    }

}