package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.auth.FirebaseAuth
import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.AuthRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockAuthRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ClipboardRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<ProcedureRepository> { ProcedureRepositoryImpl() }
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
    single<ClipboardRepository> { ClipboardRepositoryImpl(androidContext()) }

    single<AuthRepository> {
        // dev 환경에서는 Mock 객체 사용
        if (BuildConfig.FLAVOR == "dev") {
            MockAuthRepositoryImpl()
        } else {
            val auth = FirebaseAuth.getInstance()
            
            // Staging 환경에서만 Firebase Emulator 사용
            if (BuildConfig.FLAVOR == "staging") {
                try {
                    auth.useEmulator("10.0.2.2", 9099)
                } catch (e: Exception) {
                    // 이미 설정된 경우 무시
                }
            }
            
            AuthRepositoryImpl(auth)
        }
    }
}