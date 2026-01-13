package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.savedrecipes

interface SavedRecipesActionListener {
    fun onCardClick(recipeId: Long)
    fun onBookmarkClick(recipeId: Long)
}