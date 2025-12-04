package com.survivalcoding.gangnam2kiandroidstudy.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.data.model.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.tabs.DualTabBar

@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {
    var selectedDualTab by remember { mutableStateOf(0) }

    // TODO: 실제 JSON 로드 필요 (지금은 Fake Data)
    val ingredients = listOf(
        IngredientItem(
            1,
            "Tomatos",
            "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg"
        ),
        IngredientItem(
            9,
            "Onion",
            "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg"
        ),
        IngredientItem(
            8,
            "Pepper",
            "https://cdn.pixabay.com/photo/2016/03/05/22/31/pepper-1239308_1280.jpg"
        )
    )

    val recipeIngredients = listOf(
        RecipeIngredient(1, 1, 500),
        RecipeIngredient(1, 9, 50),
        RecipeIngredient(1, 8, 10)
    )

    val filteredIngredients = recipeIngredients.filter { it.recipeId == 1 }


    LazyColumn {
        item {
            DualTabBar(
                leftTab = "Ingredients",
                rightTab = "Procedure",
                selectedIndex = selectedDualTab,
                onTabSelected = { selectedDualTab = it })
        }

        item {
            RecipeCard(
                name = "Traditional spare ribs baked",
                imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                chef = "Chef John",
                time = "20 min",
                rating = 4.0,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
        }


        items(filteredIngredients.size) { index ->
            val recIng = filteredIngredients[index]
            val ingredient = ingredients.first { it.id == recIng.ingredientId }

            IngredientItem(
                name = ingredient.name,
                imageUrl = ingredient.image,
                amount = recIng.amount,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
            )
        }

    }

}
