package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeHomeCard

@Composable
fun RecipeHomeCardScreen(
    recipes: List<Recipe>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(Modifier.width(30.dp))

            LazyRow {
                items(recipes) { recipe ->
                    RecipeHomeCard(recipe = recipe)
                    Spacer(Modifier.width(15.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RecipeHomeCardScreenPreview() {
    val recipes = listOf(
        Recipe(
            1,
            "Indian",
            "Classic Greek Salad",
            "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            "Chef John",
            "20 min",
            4.0
        ),
        Recipe(
            2,
            "Indian",
            "Classic Greek Salad",
            "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            "Chef John",
            "20 min",
            4.0
        ),
        Recipe(
            3,
            "Indian",
            "Classic Greek Salad",
            "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            "Chef John",
            "20 min",
            4.0
        )
    )

    Scaffold { innerPadding ->
        RecipeHomeCardScreen(
            recipes = recipes,
            modifier = Modifier.padding(innerPadding)
        )
    }
}