package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.data.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "gangnam2-recipe-db"
        ).fallbackToDestructiveMigration() // 스키마 변경 시 초기화 (실제 운영 시에는 Migration 정의 권장)
            .build()
    }

    single { get<AppDatabase>().bookmarkDao() }
    single { get<AppDatabase>().recipeDao() }
}
