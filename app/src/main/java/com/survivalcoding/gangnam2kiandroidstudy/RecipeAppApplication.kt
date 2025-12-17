package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.procedure.ProcedureDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.savedRecipes.SavedRecipesDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.SavedRecipesRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase

class RecipeAppApplication : Application() {
    val savedRecipesDataSource by lazy { SavedRecipesDataSourceImpl() }
    val savedRecipesRepository by lazy { SavedRecipesRepositoryImpl(savedRecipesDataSource) }
    val savedRecipesUseCase by lazy { GetSavedRecipesUseCase(repository = savedRecipesRepository) }

    val savedRecipeDetailsDataSource by lazy { SavedRecipesDataSourceImpl() }
    val savedRecipeDetailsRepository by lazy {
        SavedRecipesRepositoryImpl(
            savedRecipeDetailsDataSource
        )
    }

    val procedureDataSource by lazy { ProcedureDataSourceImpl() }
    val procedureRepository by lazy { ProcedureRepositoryImpl(procedureDataSource) }


    val savedRecipeDetailsUseCase by lazy {
        GetRecipeDetailsUseCase(
            savedRecipesRepository = savedRecipeDetailsRepository,
            proceduresRepository = procedureRepository
        )
    }

}