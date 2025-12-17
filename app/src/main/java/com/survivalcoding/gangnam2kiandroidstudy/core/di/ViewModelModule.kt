package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetails.RecipeDetailsViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes.SearchRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.todo.TodoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { RecipeDetailsViewModel(get()) }
    viewModel { SavedRecipesViewModel(get()) }
    viewModel { SearchRecipesViewModel(get()) }
    viewModel { TodoViewModel(get(), get()) }
}
