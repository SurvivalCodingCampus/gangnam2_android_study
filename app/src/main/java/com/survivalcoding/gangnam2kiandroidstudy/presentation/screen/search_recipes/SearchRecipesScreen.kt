package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.CustomSearchField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SettingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppTextStyles

@Composable
fun SearchRecipesScreen(
    state: SearchRecipesState,
    onViewmodelCalled: (searchText: String, time: String, rate: String, category: String, enableBottomSheet: Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(54.dp))
        Text(
            "Search recipes",
            modifier = Modifier.fillMaxWidth(),
            style = AppTextStyles.mediumTextBold.copy(fontSize = 18.sp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(17.dp))
        Row {
            Box(modifier = Modifier.weight(1f)) {
                CustomSearchField(onValueChange = {
                    onViewmodelCalled(
                        it,
                        state.selectedTime,
                        state.selectedRate,
                        state.selectedCategory,
                        false
                    )
                })
            }
            Spacer(modifier = Modifier.width(20.dp))
            SettingButton {
                onViewmodelCalled(
                    state.searchInputText,
                    state.selectedTime,
                    state.selectedRate,
                    state.selectedCategory,
                    true
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = if (state.searchInputText.isBlank()) "Recent recipes" else "Search recipes",
                style = AppTextStyles.normalTextBold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
            Text(
                text = if (state.resultRecipes.isNotEmpty()) "${state.resultRecipes.size} results" else "0 result",
                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3),
                textAlign = TextAlign.End,
                modifier = Modifier,
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.resultRecipes) { item ->
                SmallRecipeCard(item)
            }
        }
    }
    if (state.enableBottomSheet) {
        FilterBottomSheet(
            inputText = state.searchInputText,
            time = state.selectedTime,
            rate = state.selectedRate,
            category = state.selectedCategory,
            onDismiss = { inputText, time, rate, category, enableBottomSheet ->
                Log.d(
                    "SearchRecipesScreen",
                    "inputText: $inputText, time: $time, rate: $rate, category: $category"
                )

                onViewmodelCalled(
                    inputText ?: "", time ?: "", rate ?: "", category ?: "", !enableBottomSheet
                )
            })
    }
}

@Preview(showBackground = true)
@Composable
fun SearchRecipesScreenPreview() {
    SearchRecipesScreen(state = SearchRecipesState(), onViewmodelCalled = { _, _, _, _, _ -> })
}