package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

/**
 * RecyclerView의 변경된 아이템만 갱신하도록 돕는 콜백
 * ListAdapter 등에서 사용하여 두 리스트(이전 리스트와 새 리스트)의 차이를 계산
 */
object RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    /**
     * 두 아이템이 같은 아이템인지 식별
     */
    override fun areItemsTheSame(
        oldItem: Recipe,
        newItem: Recipe
    ): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * 두 아이템의 내부 데이터(콘텐츠)가 완전히 동일한지 비교
     * areItemsTheSame이 true일 때만 호출하여 모든 필드가 같은지 확인
     */
    override fun areContentsTheSame(
        oldItem: Recipe,
        newItem: Recipe
    ): Boolean {
        return oldItem == newItem
    }
}
