package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.DataRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.DataRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
    single<DataRepository> { DataRepositoryImpl() }
}