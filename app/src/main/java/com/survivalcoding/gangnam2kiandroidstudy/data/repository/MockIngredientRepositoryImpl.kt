package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository

object MockIngredientRepositoryImpl : IngredientRepository {

    val mockIngredients = listOf(
        Ingredient(
            name = "Tomatos",
            imageUrl = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
            amount = "500g",
        ),
        Ingredient(
            name = "Beef",
            imageUrl = "https://cdn.pixabay.com/photo/2016/01/21/18/08/meet-1154341_1280.png",
            amount = "300g",
        ),
        Ingredient(
            name = "Pork",
            imageUrl = "https://cdn.pixabay.com/photo/2019/12/20/14/44/meat-4708596_1280.jpg",
            amount = "250g",
        ),
        Ingredient(
            name = "Rice",
            imageUrl = "https://cdn.pixabay.com/photo/2016/02/29/05/46/brown-rice-1228099_1280.jpg",
            amount = "2 cups",
        ),
        Ingredient(
            name = "Avocado",
            imageUrl = "https://cdn.pixabay.com/photo/2020/01/02/01/43/avocado-4734786_1280.jpg",
            amount = "1 unit",
        ),
        Ingredient(
            name = "Chicken",
            imageUrl = "https://cdn.pixabay.com/photo/2010/12/10/08/chicken-1140_1280.jpg",
            amount = "400g",
        ),
        Ingredient(
            name = "Sugar",
            imageUrl = "https://cdn.pixabay.com/photo/2014/11/28/19/10/lump-sugar-549096_1280.jpg",
            amount = "1 tbsp",
        ),
        Ingredient(
            name = "Pepper",
            imageUrl = "https://cdn.pixabay.com/photo/2016/03/05/22/31/pepper-1239308_1280.jpg",
            amount = "to taste",
        ),
        Ingredient(
            name = "Onion",
            imageUrl = "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg",
            amount = "1/2 unit",
        ),
    )

    override suspend fun getIngredientsByRecipeId(recipeId: Long): List<Ingredient> {
        return mockIngredients
    }
}