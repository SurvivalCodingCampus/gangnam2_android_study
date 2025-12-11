package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterItemButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RatingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSearchBottomSheet(
    uiState: SearchRecipeState,
    showBottomSheet: Boolean,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onTimeSelected: (String) -> Unit = {},
    onRateSelected: (String) -> Unit = {},
    onCategorySelected: (String) -> Unit = {},
    onFilterClick: (String, Double, String) -> Unit = { _, _, _ -> }
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
            containerColor = AppColors.white
        ) {
            Column(modifier = modifier.padding(horizontal = 30.dp)) {
                Spacer(Modifier.height(31.dp))
                Text(
                    text = "Filter Search",
                    modifier = Modifier.padding(horizontal = 113.dp),
                    style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(Modifier.height(20.dp))

                Column {
                    Text(text = "Time", style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(10.dp))

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        listOf("All", "Newest", "Oldest", "Popularity").forEach { time ->
                            FilterItemButton(
                                text = time,
                                isSelected = uiState.time == time,
                                onClick = { onTimeSelected(time) }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))

                Column {
                    Text(text = "Rate", style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(10.dp))

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        listOf("5", "4", "3", "2", "1").forEach { rate ->
                            RatingButton(
                                text = rate,
                                isSelected = uiState.rate == rate.toDouble(),
                                onClick = { onRateSelected(rate) }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))

                Column {
                    Text(text = "Category", style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(10.dp))

                    FlowRow(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val categories = listOf(
                            "All", "Cereal", "Vegetables", "Dinner",
                            "Chinese", "Local Dish", "Fruit", "BreakFast",
                            "Spanish", "Chinese", "Lunch"
                        )
                        categories.forEach { category ->
                            FilterItemButton(
                                text = category,
                                isSelected = uiState.category == category,
                                onClick = { onCategorySelected(category) }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(30.dp))

                SmallButton(modifier = Modifier.padding(horizontal = 70.dp), text = "Filter") {
                    onFilterClick(uiState.time, uiState.rate, uiState.category)

                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismiss()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FilterSearchBottomSheetPreview() {
    Scaffold { innerPadding ->
        FilterSearchBottomSheet(
            SearchRecipeState(),
            true,
            modifier = Modifier.padding(innerPadding)
        )
    }
}