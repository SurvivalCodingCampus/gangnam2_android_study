package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    fun auth(): FirebaseAuth {
        val auth = Firebase.auth
        if (BuildConfig.FLAVOR == "qa") {
            try {
                auth.useEmulator("10.0.2.2", 9099)
            } catch (e: Exception) {
                // 이미 연결되었거나 설정 오류 시 무시 (중복 연결 방지)
            }
        }
        return auth
    }

    @Provides
    fun firestore(): FirebaseFirestore {
        val firestore = Firebase.firestore
        if (BuildConfig.FLAVOR == "qa") {
            try {
                firestore.useEmulator("10.0.2.2", 8080)
            } catch (e: Exception) {
                // 이미 연결되었거나 설정 오류 시 무시
            }
        }
        return firestore
    }
}
