package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.dsl.module

val flavorModule = module {
    single {
        val firestore = Firebase.firestore
        try {
            firestore.useEmulator("10.0.2.2", 8080)
        } catch (e: Exception) {
            // Already configured
        }
        firestore
    }
    single<BookmarkRepository> { BookmarkRepositoryImpl(get(), get()) }
}