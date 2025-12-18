package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.SavedRecipesRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SavedRecipesRepository> { SavedRecipesRepositoryImpl(get()) }
    single<ProcedureRepository> { ProcedureRepositoryImpl(get()) }
}