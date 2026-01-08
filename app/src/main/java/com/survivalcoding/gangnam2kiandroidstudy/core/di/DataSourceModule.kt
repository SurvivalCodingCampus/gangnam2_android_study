package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RemoteRecipeDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<RecipeDataSource> {
        if (BuildConfig.FLAVOR == "dev") {
            MockRecipeDataSourceImpl()
        } else {
            RemoteRecipeDataSourceImpl(get())
        }
    }
}
