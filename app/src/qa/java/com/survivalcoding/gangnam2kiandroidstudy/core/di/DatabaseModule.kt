package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.data.local.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.local.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.inMemoryDatabaseBuilder(
            androidContext(),
            AppDatabase::class.java
        ).build()
    }

    single<BookmarkDao> {
        get<AppDatabase>().bookmarkDao()
    }

    single<BookmarkRepository> {
        BookmarkRepositoryImpl(get())
    }
}
