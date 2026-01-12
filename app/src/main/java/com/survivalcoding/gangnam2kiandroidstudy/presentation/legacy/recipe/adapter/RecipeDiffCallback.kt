package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

object RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(
        oldItem: Recipe,
        newItem: Recipe
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Recipe,
        newItem: Recipe
    ): Boolean {
        return oldItem == newItem
    }
}
