package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.IngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockChefDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockIngredientDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockProcedureDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeIngredientDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeIngredientDataSource
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
        return MockRecipeDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideChefDataSource(): ChefDataSource {
        return MockChefDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideIngredientDataSource(): IngredientDataSource {
        return MockIngredientDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideProcedureDataSource(): ProcedureDataSource {
        return MockProcedureDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideRecipeIngredientDataSource(): RecipeIngredientDataSource {
        return MockRecipeIngredientDataSourceImpl()
    }

}