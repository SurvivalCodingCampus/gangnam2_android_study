package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeCategorySelector(
    modifier: Modifier = Modifier,
    selectCategory: RecipeCategory = RecipeCategory.ALL,
    onSelectCategory: (RecipeCategory) -> Unit = {},
    recipeCategories: List<RecipeCategory> = RecipeCategory.entries,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(Modifier.width(30.dp))

            LazyRow {
                items(recipeCategories) { category ->
                    Box(
                        modifier = Modifier
                            .clickable { onSelectCategory(category) }
                    ) {
                        val selectModifier = if (selectCategory == category) {
                            Modifier
                                .background(color = AppColors.primary100, shape = RoundedCornerShape(10.dp))
                                .padding(horizontal = 20.dp, vertical = 7.dp)
                        } else {
                            Modifier.padding(horizontal = 20.dp, vertical = 7.dp)
                        }

                        Text(
                            text = category.displayName,
                            modifier = selectModifier,
                            style = AppTextStyles.smallerTextBold.copy(fontWeight = FontWeight.SemiBold),
                            color = if (selectCategory == category) AppColors.white else AppColors.primary80
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RecipeCategorySelectorPreview() {
    Scaffold { innerPadding ->
        RecipeCategorySelector(
            modifier = Modifier.padding(innerPadding)
        )
    }
}