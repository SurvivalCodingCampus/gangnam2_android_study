package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientResponse
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.ProcedureResponse

interface AssetDataSource {

    suspend fun getIngredients(): IngredientResponse
    suspend fun getProcedures(): ProcedureResponse
}