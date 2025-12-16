package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AssetDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AssetDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngridentRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngridentRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase

class AppApplication : Application() {
    val recipeDataSource: RecipeDataSource by lazy { RecipeDataSourceImpl() }
    val assetDataSource: AssetDataSource by lazy { AssetDataSourceImpl(this) }

    val recipeRepository: RecipeRepository by lazy { RecipeRepositoryImpl(recipeDataSource) }
    val ingridentRepository: IngridentRepository by lazy { IngridentRepositoryImpl(assetDataSource) }
    val procedureRepository: ProcedureRepository by lazy { ProcedureRepositoryImpl(assetDataSource) }

    val getRecipesUseCase: GetRecipesUseCase by lazy { GetRecipesUseCase(recipeRepository) }
    val getRecipeDetailsUseCase: GetRecipeDetailsUseCase by lazy {
        GetRecipeDetailsUseCase(
            recipeRepository,
            ingridentRepository,
            procedureRepository
        )
    }
}