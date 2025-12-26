package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipeDetail.RecipeDetailViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchRecipesViewModel(recipeRepository = get()) }
    viewModel { SavedRecipesViewModel(getSavedRecipesUseCase = get()) }
    viewModel { HomeViewModel(recipeRepository = get(), bookmarkRepository = get()) }
    viewModel { RecipeDetailViewModel(getRecipeDetailsUseCase = get(), copyLinkUseCase = get()) }
    viewModel { SplashViewModel(networkStatusRepository = get()) }
}