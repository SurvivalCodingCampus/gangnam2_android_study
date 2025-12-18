package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.procedure.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.procedure.ProcedureDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.savedRecipes.SavedRecipesDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.savedRecipes.SavedRecipesDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<ProcedureDataSource> { ProcedureDataSourceImpl() }
    single<SavedRecipesDataSource> { SavedRecipesDataSourceImpl() }
}