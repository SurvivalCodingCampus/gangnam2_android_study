package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.NetworkStatusDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.NetworkStatusDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.NetworkStatusRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.NetworkStatusRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single<NetworkStatusDataSource> {
        NetworkStatusDataSourceImpl(
            context = androidContext()
        )
    }

    single<NetworkStatusRepository> {
        NetworkStatusRepositoryImpl(get())
    }

    single<BookmarkRepository> {
        BookmarkRepositoryImpl(get(), get())
    }
}