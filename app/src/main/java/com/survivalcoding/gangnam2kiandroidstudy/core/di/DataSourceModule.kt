package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockUserDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSoureImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.UserDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val datasourceModule = module {
    // single<Interface> { 구현체 } 형태로 사용
    single<RecipeDataSource> { MockRecipeDataSourceImpl() }
    single<UserDataSource> { MockUserDataSourceImpl() }
    single<ProcedureDataSource> { ProcedureDataSoureImpl() }
//    single<NetworkStatusDataSource> {
//        NetworkStatusDataSourceImpl(
//            androidContext()
//        )
//    }
}