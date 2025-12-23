package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockNetworkStatusDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.NetworkStatusDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.NetworkStatusRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.NetworkStatusRepository
import org.koin.dsl.module

val networkModule = module {

    single<NetworkStatusDataSource> {

        MockNetworkStatusDataSourceImpl()
    }

    single<NetworkStatusRepository> {
        NetworkStatusRepositoryImpl(get())
    }
}