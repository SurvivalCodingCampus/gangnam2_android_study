package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetRecipeDetailsUseCase> { GetRecipeDetailsUseCase(get(), get()) }
    single<GetSavedRecipesUseCase> { GetSavedRecipesUseCase(get()) }
}