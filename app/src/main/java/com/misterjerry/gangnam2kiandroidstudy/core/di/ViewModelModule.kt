package com.misterjerry.gangnam2kiandroidstudy.core.di

import com.misterjerry.gangnam2kiandroidstudy.presentation.screen.home.HomeViewModel
import com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail.SavedRecipeDetailsViewModel
import com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes.SavedRecipesViewModel
import com.misterjerry.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesViewModel
import com.misterjerry.gangnam2kiandroidstudy.presentation.screen.sign_in.SignInViewModel
import com.misterjerry.gangnam2kiandroidstudy.presentation.screen.sign_up.SignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel) //viewModelOf 사용
    viewModel { (savedRecipeId: Int) ->
        SavedRecipeDetailsViewModel(
            savedRecipeId,
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel { SavedRecipesViewModel(get(), get(), get()) }
    viewModel { SearchRecipesViewModel(get(), get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }

}