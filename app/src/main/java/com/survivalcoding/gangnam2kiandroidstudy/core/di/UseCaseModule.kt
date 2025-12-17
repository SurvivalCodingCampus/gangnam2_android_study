package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.usercase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usercase.GetSavedRecipesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetSavedRecipesUseCase(get()) }
    // single { ToggleBookmarkUseCase(get()) }
    single { GetRecipeDetailsUseCase(get(), get(), get(), get()) }
}
