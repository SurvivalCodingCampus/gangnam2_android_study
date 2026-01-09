package com.survivalcoding.gangnam2kiandroidstudy.recipereader.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.recipereader.domain.model.ReaderRecipe

interface ReaderRepository {
    suspend fun getRecipes(): List<ReaderRecipe>
}
