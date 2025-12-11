package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter

enum class CategoryFilter(
    val label: String,
    val hasStar: Boolean = false
) {
    ALL("All"),
    CEREAL("Cereal"),
    VEGETABLES("Vegetables"),
    DINNER("Dinner", hasStar = true),
    CHINESE("Chinese"),
    LOCAL("Local Dish"),
    FRUIT("Fruit"),
    BREAKFAST("Breakfast"),
    SPANISH("Spanish"),
    LUNCH("Lunch")
}
