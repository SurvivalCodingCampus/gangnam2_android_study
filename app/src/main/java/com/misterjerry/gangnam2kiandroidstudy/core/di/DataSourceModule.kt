package com.misterjerry.gangnam2kiandroidstudy.core.di

import androidx.credentials.CredentialManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.misterjerry.gangnam2kiandroidstudy.BuildConfig
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
        if (BuildConfig.FLAVOR == "qa") {
            try {
                auth.useEmulator("10.0.2.2", 9099)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        auth
    }
    single<CredentialManager> { CredentialManager.create(androidContext()) }

    single<FirebaseFirestore>(createdAtStart = true) {
        val firestore = Firebase.firestore
        if (BuildConfig.FLAVOR == "qa") {
            try {
                firestore.useEmulator("10.0.2.2", 8080)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        firestore
    }
}