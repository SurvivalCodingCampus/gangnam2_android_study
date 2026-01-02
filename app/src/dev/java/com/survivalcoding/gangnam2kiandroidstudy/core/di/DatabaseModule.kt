package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockUserDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.UserDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.dsl.module

val databaseModule = module {
    single<UserDataSource> { MockUserDataSourceImpl() }
    single<BookmarkRepository> { BookmarkRepositoryImpl(get()) }
}
