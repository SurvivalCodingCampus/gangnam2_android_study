package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSoureImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.local.dao.RecipeDao
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RoomRecipeDataSource
import org.koin.dsl.module

val datasourceModule = module {
    // single<Interface> { 구현체 } 형태로 사용
    single<RecipeDataSource> { RoomRecipeDataSource(get<RecipeDao>()) }

    single<ProcedureDataSource> { ProcedureDataSoureImpl() }

}