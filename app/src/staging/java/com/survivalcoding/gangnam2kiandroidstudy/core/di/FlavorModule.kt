package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.auth.FirebaseAuth
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.AuthRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.dsl.module

val flavorModule = module {
    single<BookmarkRepository> { MockBookmarkRepositoryImpl() }
}