package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.IngredientDataSource

class IngredientRepositoryImpl private constructor(
    private val ingredientDataSource: IngredientDataSource
) : IngredientRepository {

    companion object {
        @Volatile private var instance: IngredientRepository? = null

        fun getInstance(ingredientDataSource: IngredientDataSource) =
            instance ?: synchronized(this) {
                instance ?: IngredientRepositoryImpl(ingredientDataSource).also { instance = it }
            }
    }
}