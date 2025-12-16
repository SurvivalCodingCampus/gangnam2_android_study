package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Procedure

interface ProcedureRepository {
    suspend fun getProcedureByRecipeId(recipeId: Int): Result<List<Procedure>>
}