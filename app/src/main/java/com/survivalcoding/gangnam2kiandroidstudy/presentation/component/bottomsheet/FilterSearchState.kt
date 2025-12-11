package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet

import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.CategoryFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.RateFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.TimeFilter

data class FilterSearchState(
    val time: TimeFilter = TimeFilter.ALL,
    val rate: RateFilter = RateFilter.FIVE,
    val category: CategoryFilter = CategoryFilter.ALL
)
