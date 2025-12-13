package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetails

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecipeDetailsScreen(
    id: Int,
) {
    Box() {
        Text(text = "Recipe Details")
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    RecipeDetailsScreen(
        id = 1,
    )
}
