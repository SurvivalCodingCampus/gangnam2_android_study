package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory

fun String.toCategory(): RecipeCategory {
    return when(this){
        "All" -> RecipeCategory.ALL
        "Indian" -> RecipeCategory.INDIAN
        "Italian" -> RecipeCategory.ITALIAN
        "Asian" -> RecipeCategory.ASIAN
        "Chinese" -> RecipeCategory.CHINESE
        "Fruit" -> RecipeCategory.FRUIT
        "Vegetables" -> RecipeCategory.VEGETABLES
        "Protein" -> RecipeCategory.PROTEIN
        "Cereal" -> RecipeCategory.CEREAL
        "Local Dishes", "Local Dish" -> RecipeCategory.LOCAL_DISHES
        "Dinner"  -> RecipeCategory.DINNER
        "BreakFast" -> RecipeCategory.BREAKFAST
        "Spanish" -> RecipeCategory.SPANISH
        "Lunch" -> RecipeCategory.LUNCH
        else -> RecipeCategory.NONE
    }
}