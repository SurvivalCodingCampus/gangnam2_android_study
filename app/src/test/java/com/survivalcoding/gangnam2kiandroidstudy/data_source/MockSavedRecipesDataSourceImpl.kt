package com.survivalcoding.gangnam2kiandroidstudy.data_source

import com.survivalcoding.gangnam2kiandroidstudy.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.model.RecipeDto

class MockSavedRecipesDataSourceImpl : SavedRecipesDataSource {

    val mockSavedRecipeList = listOf(
        Recipe(
            category = "",
            chef = "Test",
            id = 1,
            image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            name = "test",

            rating = 1.0,
            time = "1 min"
        )
    )

    override fun getSavedRecipes(): RecipeDto {
        return RecipeDto(mockSavedRecipeList)
    }
}