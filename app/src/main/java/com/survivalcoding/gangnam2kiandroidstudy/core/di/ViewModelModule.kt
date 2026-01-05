package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.lifecycle.SavedStateHandle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail.RecipeDetailViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.signin.SignInViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.signup.SignUpViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            getFilteredRecipesUseCase = get(),
            toggleBookmarkUseCase = get()
        )
    }
    viewModel {
        SearchViewModel(
            getAllRecipesUseCase = get(),
            searchRecipeByKeywordUseCase = get()
        )
    }
    viewModel {
        SavedRecipesViewModel(
            getSavedRecipesUseCase = get(),
            toggleBookmarkUseCase = get()
        )
    }
    viewModel { (recipeId: Int) ->
        val savedStateHandle = get<SavedStateHandle>()
        savedStateHandle["recipeId"] = recipeId

        RecipeDetailViewModel(
            getRecipeDetailsUseCase = get(),
            copyLinkUseCase = get(),
            savedStateHandle = savedStateHandle
        )
    }
    viewModel {
        SplashViewModel(observeNetworkStatusUseCase = get())
    }
    viewModel {
        SignInViewModel(
            signInUseCase = get(),
            googleSignInUseCase = get()
        )
    }
    viewModel {
        SignUpViewModel(
            signUpUseCase = get(),
            googleSignInUseCase = get()
        )
    }
}