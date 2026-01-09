package com.survivalcoding.gangnam2kiandroidstudy.recipereader.presentation

import com.survivalcoding.gangnam2kiandroidstudy.recipereader.domain.model.ReaderRecipe

data class ReaderState(
    val recipes: List<ReaderRecipe> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
