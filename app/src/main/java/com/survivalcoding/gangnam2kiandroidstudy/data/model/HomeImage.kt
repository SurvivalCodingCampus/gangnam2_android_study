package com.survivalcoding.gangnam2kiandroidstudy.data.model

import com.survivalcoding.gangnam2kiandroidstudy.R
import kotlinx.serialization.Serializable

@Serializable
enum class HomeImage(val resId: Int) {
    FOOD1(R.drawable.food_1),
    FOOD2(R.drawable.food_2),
    FOOD3(R.drawable.food_3),
    FOOD4(R.drawable.food_4),
    FOOD5(R.drawable.food_5),
}
