package com.misterjerry.gangnam2kiandroidstudy.core.di

import com.misterjerry.gangnam2kiandroidstudy.data.repository.AuthRepositoryImpl
import com.misterjerry.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.misterjerry.gangnam2kiandroidstudy.data.repository.RecipesRepositoryImpl
import com.misterjerry.gangnam2kiandroidstudy.data.repository.SavedRecipesFirestoreRepositoryImpl
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.RecipesRepository
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<RecipesRepository> { RecipesRepositoryImpl(get()) }
    single<ProcedureRepository> { ProcedureRepositoryImpl(get()) }



    single<AuthRepository> { AuthRepositoryImpl(get()) }
    // Room implementation (commented out to switch to Firestore)
    // single<SavedRecipesRepository> { SavedRecipesRepositoryImpl(get()) }

    // Firestore implementation
    single<SavedRecipesRepository> { SavedRecipesFirestoreRepositoryImpl(get(), get()) }
}