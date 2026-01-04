package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in.SignInViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up.SignUpViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }

    viewModel {
        HomeViewModel(
            getRecipesUseCase = get(),
            getBookmarkedRecipeIdsUseCase = get(),
            addBookmarkUseCase = get(),
            removeBookmarkUseCase = get(),
        )
    }
    viewModel {
        SavedRecipesViewModel(
            getSavedRecipesUseCase = get(),
            getRecipeDetailsUseCase = get(),
            addBookmarkUseCase = get(),
            removeBookmarkUseCase = get(),
        )
    }
    viewModel {
        SearchRecipesViewModel(
            getRecipesUseCase = get(),
        )
    }
    viewModel {
        IngredientViewModel(
            getRecipeDetailsUseCase = get(),
            getRecipeProcedureUseCase = get(),
            copyLinkUseCase = get(),
            addBookmarkUseCase = get(),
            removeBookmarkUseCase = get(),
            getBookmarkedRecipeIdsUseCase = get(),
        )
    }
    viewModel {
        SplashViewModel(get())
    }
}