package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.AuthRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.dsl.module

val flavorModule = module {
    single { Firebase.firestore }
    // TODO: Add real implementation
    single<BookmarkRepository> { BookmarkRepositoryImpl(get(), get()) }
}