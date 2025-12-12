package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecipeCategorySelector(
    selected: HomeCategory,
    onSelectCategory: (HomeCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(HomeCategory.values()) { category ->
            CategoryChip(
                text = category.label,
                isSelected = selected == category,
                onClick = { onSelectCategory(category) }
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun HomeCategorySelectorPreview() {
    var selected by rememberSaveable { mutableStateOf(HomeCategory.ALL) }

    RecipeCategorySelector(
        selected = selected,
        onSelectCategory = { selected = it }
    )
}