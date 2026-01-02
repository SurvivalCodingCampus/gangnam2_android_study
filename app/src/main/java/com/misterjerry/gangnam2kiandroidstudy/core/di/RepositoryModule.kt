package com.misterjerry.gangnam2kiandroidstudy.core.di

import com.misterjerry.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.misterjerry.gangnam2kiandroidstudy.data.repository.RecipesRepositoryImpl
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.RecipesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<RecipesRepository> { RecipesRepositoryImpl(get()) }
    single<ProcedureRepository> { ProcedureRepositoryImpl(get()) }
}