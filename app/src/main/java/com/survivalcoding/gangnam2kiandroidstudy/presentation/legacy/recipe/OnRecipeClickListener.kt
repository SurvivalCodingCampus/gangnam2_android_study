package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe

// 콜백 인터페이스
interface OnRecipeClickListener {
    fun onRecipeClick(recipeId: Int)
    fun onBookmarkClick(recipeId: Int)
}