package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.auth.FirebaseAuth
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.AuthRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.dsl.module

val flavorModule = module {
    single<BookmarkRepository> { MockBookmarkRepositoryImpl() }
    
    single<AuthRepository> {
        val auth = FirebaseAuth.getInstance()
        try {
            // Android Emulator loopback address
            auth.useEmulator("10.0.2.2", 9099)
        } catch (e: Exception) {
            // Emulator might be already set
        }
        AuthRepositoryImpl(auth)
    }
}