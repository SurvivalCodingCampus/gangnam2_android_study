package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.FilterRecipesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { FilterRecipesUseCase(get()) }
}