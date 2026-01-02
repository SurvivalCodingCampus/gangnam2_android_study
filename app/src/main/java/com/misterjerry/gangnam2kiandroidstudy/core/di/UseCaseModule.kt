package com.misterjerry.gangnam2kiandroidstudy.core.di

import com.misterjerry.gangnam2kiandroidstudy.domain.use_case.AddSavedRecipeUseCase
import com.misterjerry.gangnam2kiandroidstudy.domain.use_case.CopyLinkUseCase
import com.misterjerry.gangnam2kiandroidstudy.domain.use_case.DeleteSavedRecipeUseCase
import com.misterjerry.gangnam2kiandroidstudy.domain.use_case.GetAllRecipesUseCase
import com.misterjerry.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import com.misterjerry.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetRecipeDetailsUseCase> { GetRecipeDetailsUseCase(get(), get()) }
    single<GetSavedRecipesUseCase> { GetSavedRecipesUseCase(get()) }
    single<DeleteSavedRecipeUseCase> { DeleteSavedRecipeUseCase(get()) }
    single<CopyLinkUseCase> { CopyLinkUseCase(get()) }
    single<GetAllRecipesUseCase> { GetAllRecipesUseCase(get()) }
    single<AddSavedRecipeUseCase> { AddSavedRecipeUseCase(get()) }
}