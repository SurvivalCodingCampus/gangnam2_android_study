package com.survivalcoding.gangnam2kiandroidstudy.data.model

data class RecipeSearchFilter(
    val time: TimeFilterType? = null,
    val rate: RateFilterType? = null,
    val category: CategoryFilterType? = null,
) {
    fun isNull(): Boolean {
        return time == null && rate == null && category == null
    }

    fun isNotNull(): Boolean {
        return !isNull()
    }
}