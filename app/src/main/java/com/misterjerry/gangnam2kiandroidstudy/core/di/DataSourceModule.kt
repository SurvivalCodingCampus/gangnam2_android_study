package com.misterjerry.gangnam2kiandroidstudy.core.di

import android.util.Log
import androidx.credentials.CredentialManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.misterjerry.gangnam2kiandroidstudy.data.data_source.procedure.ProcedureDataSource
import com.misterjerry.gangnam2kiandroidstudy.data.data_source.procedure.ProcedureDataSourceImpl
import com.misterjerry.gangnam2kiandroidstudy.data.data_source.savedRecipes.RecipesDataSource
import com.misterjerry.gangnam2kiandroidstudy.data.data_source.savedRecipes.RecipesDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataSourceModule = module {
    single<ProcedureDataSource> { ProcedureDataSourceImpl() }
    single<RecipesDataSource> { RecipesDataSourceImpl() }

    single<FirebaseAuth> {
        val auth = Firebase.auth
        val context = androidContext()
        Log.d("dataSourceModule", "Current Package Name: ${context.packageName}")
        // BuildConfig.FLAVOR 대신 패키지 이름으로 확인
        if (context.packageName.endsWith(".qa")) {
            // 에뮬레이터 IP (10.0.2.2)와 Auth 포트 (9099) 설정
            try {
                auth.useEmulator("10.0.2.2", 9099)
                Log.d("dataSourceModule", "dataSourceModule: 진입")
            } catch (e: Exception) {
                // 이미 설정된 경우 예외 무시
            }
        }

        auth
    }
    single<CredentialManager> { CredentialManager.create(androidContext()) }

}