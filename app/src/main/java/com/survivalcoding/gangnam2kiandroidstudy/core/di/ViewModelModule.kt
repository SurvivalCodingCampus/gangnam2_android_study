package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail.RecipeDetailsViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes.SearchRecipesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { RecipeDetailsViewModel(get()) }
    viewModel { SavedRecipesViewModel(get(), get()) }
    viewModel { SearchRecipesViewModel(get()) }
}