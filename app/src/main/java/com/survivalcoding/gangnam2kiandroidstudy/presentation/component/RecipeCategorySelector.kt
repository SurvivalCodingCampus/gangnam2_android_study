package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecipeCategorySelector(
    items: List<String> = emptyList(),
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    selectedCategory: String,
    onCategoryClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .height(51.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            val startPadding = if (item == items.first()) contentPadding.calculateStartPadding(LocalLayoutDirection.current) else 0.dp
            val endPadding = if (item == items.last()) contentPadding.calculateEndPadding(LocalLayoutDirection.current) else 0.dp

            FilterTabButton(
                item,
                selectedCategory == item,
                modifier = Modifier.padding(
                    start = startPadding,
                    top = contentPadding.calculateTopPadding(),
                    end = endPadding,
                    bottom = contentPadding.calculateBottomPadding()
                ),
                onClick = { onCategoryClick(item) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCategorySelectorPreview() {
    RecipeCategorySelector(
        listOf("All", "Indian", "Italian", "Asian", "Chinese", "Fruit", "Vegetables", "Protein", "Cereal", "Local Dishes"),
        selectedCategory = "Indian",
        onCategoryClick = {}
    )
}