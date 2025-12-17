package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientDto

interface IngredientDataSource {
    suspend fun getIngredients(): List<IngredientDto>
}
