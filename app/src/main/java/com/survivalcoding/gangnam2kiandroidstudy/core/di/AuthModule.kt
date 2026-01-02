package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.collection.BuildConfig
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.AuthRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import org.koin.dsl.module


val authModule = module {

    single<FirebaseAuth> {
        val auth = FirebaseAuth.getInstance()

        if (BuildConfig.FLAVOR == "qa") {
            auth.useEmulator("10.0.2.2", 9099)
        }

        auth
    }

    single<FirebaseFirestore> {
        val db = FirebaseFirestore.getInstance()

        if (BuildConfig.FLAVOR == "qa") {
            db.useEmulator("10.0.2.2", 8080)
        }

        db
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}