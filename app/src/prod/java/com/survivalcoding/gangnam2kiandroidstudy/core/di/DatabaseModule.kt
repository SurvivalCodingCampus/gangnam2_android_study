package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.data.local.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.local.dao.BookmarkDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single<BookmarkDao> {
        get<AppDatabase>().bookmarkDao()
    }

    single<RecipeDao> {
        get<AppDatabase>().recipeDao()
    }
}
