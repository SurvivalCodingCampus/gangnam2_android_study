package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            SavedRecipesDatabase::class.java,
            "saved_recipes_db" // DB 파일 이름
        )
            .build()
    }

    single { get<SavedRecipesDatabase>().savedRecipesDao() }
}
