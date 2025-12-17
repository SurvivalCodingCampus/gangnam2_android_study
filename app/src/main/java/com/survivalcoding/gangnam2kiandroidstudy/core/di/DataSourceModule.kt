package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<RecipeDataSource> { MockRecipeDataSourceImpl() }
}