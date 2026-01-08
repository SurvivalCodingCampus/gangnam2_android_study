package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.util.RealNetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.data.database.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RoomBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val versionModule = module {
    single<NetworkMonitor> { RealNetworkMonitor(androidContext()) }

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "recipe-db",
        ).build()
    }
    single { get<AppDatabase>().bookmarkDao() }
    single { get<AppDatabase>().recipeDao() }
    single<BookmarkRepository> { RoomBookmarkRepositoryImpl(get()) }
}