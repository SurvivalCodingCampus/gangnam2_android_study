package com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.saved_recipe

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

object RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    // 아이템이 같은 대상인지 (id 기준)
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    // 내용이 같은지 (data class면 == 로 충분)
    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}
