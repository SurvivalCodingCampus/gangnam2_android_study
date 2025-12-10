package com.survivalcoding.gangnam2kiandroidstudy.data.model

enum class CategoryFilterType(val label: String) {
    ALL("All"),
    CEREAL("Cereal"),
    VEGETABLES("Vegetables"),
    DINNER("Dinner"),
    CHINESE("Chinese"),
    LOCAL_DISH("Local Dish"),
    FRUIT("Fruit"),
    BREAKFAST("Breakfast"),
    SPANISH("Spanish"),
    LUNCH("Lunch");

    fun matches(category: String?): Boolean {
        return category == null || this == ALL || category == label
    }
}