package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.DeleteSavedRecipeUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetAllRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetRecipeDetailsUseCase> { GetRecipeDetailsUseCase(get(), get()) }
    single<GetSavedRecipesUseCase> { GetSavedRecipesUseCase(get()) }
    single<DeleteSavedRecipeUseCase> { DeleteSavedRecipeUseCase(get()) }
    single<CopyLinkUseCase> { CopyLinkUseCase(get()) }
    single<GetAllRecipesUseCase> { GetAllRecipesUseCase(get()) }
}