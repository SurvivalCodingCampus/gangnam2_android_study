package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockUserDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSoureImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngridentRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngridentRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase

class AppApplication : Application() {

    val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(
            recipeDataSource = MockRecipeDataSourceImpl()
        )
    }
    val bookmarkRepository: BookmarkRepository by lazy {
        BookmarkRepositoryImpl(
            userDataSource = MockUserDataSourceImpl()
        )
    }
    val procedureRepository: ProcedureRepository by lazy {
        ProcedureRepositoryImpl(
            procedureDataSource = ProcedureDataSoureImpl()
        )
    }

    val ingridentRepository: IngridentRepository by lazy {
        IngridentRepositoryImpl(
            recipeDataSource = MockRecipeDataSourceImpl()
        )
    }
    val getSavedRecipesUseCase: GetSavedRecipesUseCase by lazy {
        GetSavedRecipesUseCase(
            recipeRepository = recipeRepository,
            bookmarkRepository = bookmarkRepository
        )
    }

    val getRecipeDetailsUseCase: GetRecipeDetailsUseCase by lazy {
        GetRecipeDetailsUseCase(
            recipeRepository = recipeRepository,
            procedureRepository = procedureRepository,
            ingridentRepository = ingridentRepository
        )
    }

}