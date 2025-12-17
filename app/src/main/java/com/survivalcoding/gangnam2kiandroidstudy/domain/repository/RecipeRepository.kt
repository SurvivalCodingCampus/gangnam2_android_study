package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

interface RecipeRepository {
    //전체 레시피 조회
    suspend fun getRecipes(): List<Recipe>

    // 단일 레시피 조회
    suspend fun getRecipeById(id: Int): Recipe?

    // 여러 ID로 레시피 조회
    suspend fun getRecipesByIds(ids: List<Int>): List<Recipe>
}