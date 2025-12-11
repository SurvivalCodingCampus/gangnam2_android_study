package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun RecipeCategorySelector(
    modifier: Modifier = Modifier,
    category: List<String>,
    onSelectCategory: (String) -> Unit= {},
) {
    var selectedCategory by remember { mutableStateOf("All") }

    LazyRow(
       modifier = modifier
           .fillMaxWidth()
           .background(AppColors.white)
           .padding(vertical = 10.dp)
    ) {
        item {
            Spacer(modifier = Modifier.width(30.dp))
        }
        items(category) {
            CategoryChip(
                text = it,
                isSelected = it == selectedCategory,
                onClick = {
                    selectedCategory = it
                    onSelectCategory(it)
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun RecipeCategorySelectorPreview() {
    RecipeCategorySelector(
        category = listOf("Chinese", "Korean", "Japanese", "Western"),


    )
}