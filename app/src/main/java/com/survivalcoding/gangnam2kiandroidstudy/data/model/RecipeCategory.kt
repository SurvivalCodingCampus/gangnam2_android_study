package com.survivalcoding.gangnam2kiandroidstudy.data.model

enum class RecipeCategory(val displayName: String) {
    ALL("All"),
    INDIAN("Indian"),
    ITALIAN("Italian"),
    ASIAN("Asian"),
    CHINESE("Chinese"),
    FRUIT("Fruit"),
    VEGETABLES("Vegetables"),
    PROTEIN("Protein"),
    CEREAL("Cereal"),
    LOCAL_DISHES("Local Dishes");

    companion object {
        fun getRecipeCategoryByDisplayName(name: String): RecipeCategory {
            return RecipeCategory.entries.find { it.displayName.equals(name, ignoreCase = true) } ?: ALL
        }
    }
}
