package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.savedrecipes

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

class RecipeDiffCallback(
    private val oldList: List<Recipe>,
    private val newList: List<Recipe>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}