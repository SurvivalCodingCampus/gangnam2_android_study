package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.recipe.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun NewRecipeCard(
    recipe: Recipe,
    enableAnimation: Boolean,
) {
    Box(
        modifier = Modifier
            .height(127.dp)
            .aspectRatio(251 / 127f)
            .background(AppColors.white, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Column {
            Text(recipe.name, style = AppTextStyles.smallTextBold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("By ${recipe.chef}", style = AppTextStyles.smallerTextRegular)
        }

        if (!enableAnimation) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.TopEnd)
                    .background(AppColors.gray3, CircleShape)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun NewRecipeCardPreview() {
    NewRecipeCard(
        recipe = Recipe(
            id = 1,
            name = "Steak with tomato sauce",
            chef = "James Milner",
            time = "20 min",
            category = "Dinner",
            rating = 4.0,
            imageUrl = "",
            createdAt = System.currentTimeMillis()
        ),
        enableAnimation = true
    )
}
