package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.auth.FirebaseAuth
import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
import org.koin.dsl.module

val authModule = module {
    single {
        val auth = FirebaseAuth.getInstance()
        if (BuildConfig.FLAVOR != "prod") {
            auth.useEmulator("10.0.2.2", 9099)
        }
        auth
    }
}