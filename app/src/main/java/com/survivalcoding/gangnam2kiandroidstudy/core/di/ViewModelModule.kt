package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes.SearchRecipesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SearchRecipesViewModel(
            recipeRepository = get(),
            filterRecipesUsecase = get(),
        )
    }
}