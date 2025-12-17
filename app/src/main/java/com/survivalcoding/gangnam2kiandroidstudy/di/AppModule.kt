package com.survivalcoding.gangnam2kiandroidstudy.di

import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.datasource.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.SavedRecipesRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import org.koin.dsl.module

val appModule = module {

    // DataSource
    single { RecipeDataSourceImpl() }

    // Repository
    single<RecipeRepository> {
        RecipeRepositoryImpl(
            dataSource = get()
        )
    }

    single<BookmarkRepository> {
        BookmarkRepositoryImpl(
            recipeRepository = get()
        )
    }

    single<SavedRecipesRepository> {
        SavedRecipesRepositoryImpl(
            recipeRepository = get()
        )
    }

    // UseCase
    single {
        GetSavedRecipesUseCase(
            bookmarkRepository = get(),
            savedRecipesRepository = get()
        )
    }
}
