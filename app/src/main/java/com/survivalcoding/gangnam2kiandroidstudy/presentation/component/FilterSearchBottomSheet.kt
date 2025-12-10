package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import android.R.attr.text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.FilterSearchState
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSearchBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismiss: () -> Unit = {},
    currentFilterState: FilterSearchState,
    onApplyFilter: (FilterSearchState) -> Unit = {},
) {
    var filterState by remember { mutableStateOf(currentFilterState) }

    ModalBottomSheet(
        containerColor = AppColors.white,
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Filter Search",
                style = AppTextStyles.smallTextBold,
                modifier = Modifier
                    .padding(top = 31.dp),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp),
            ) {
                Text(
                    text = "Time",
                    style = AppTextStyles.smallTextBold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    listOf("All", "Newest", "Oldest", "Popularity").forEach { text ->
                        FilterButton(
                            text = text,
                            isSelected = text == filterState.selectedTimeText,
                            onClick = {
                                val newTime =
                                    if (text == filterState.selectedTimeText) null else text
                                filterState = filterState.copy(selectedTimeText = newTime)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Rate",
                    style = AppTextStyles.smallTextBold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    listOf("5", "4", "3", "2", "1").forEach { text ->
                        RatingButton(
                            text = text,
                            isSelected = text == filterState.selectedRateText,
                            onClick = {
                                val newRate =
                                    if (text == filterState.selectedRateText) null else text
                                filterState = filterState.copy(selectedRateText = newRate)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Category",
                    style = AppTextStyles.smallTextBold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FilterButton(
                        text = "All",
                        isSelected = "All" == filterState.selectedCategoryText,
                        onClick = {
                            val newCategory =
                                if ("All" == filterState.selectedCategoryText) null else "All"
                            filterState = filterState.copy(selectedCategoryText = newCategory)
                        }
                    )
                    FilterButton(
                        text = "Cereal",
                        isSelected = "Cereal" == filterState.selectedCategoryText,
                        onClick = {
                            val newCategory =
                                if ("Cereal" == filterState.selectedCategoryText) null else "Cereal"
                            filterState = filterState.copy(selectedCategoryText = newCategory)
                        }
                    )
                    FilterButton(
                        text = "Vegetables",
                        isSelected = "Vegetables" == filterState.selectedCategoryText,
                        onClick = {
                            val newCategory =
                                if ("Vegetables" == filterState.selectedCategoryText) null else "Vegetables"
                            filterState = filterState.copy(selectedCategoryText = newCategory)
                        }
                    )
                    RatingButton(
                        text = "Dinner",
                        isSelected = "Dinner" == filterState.selectedCategoryText,
                        onClick = {
                            val newCategory =
                                if ("Dinner" == filterState.selectedCategoryText) null else "Dinner"
                            filterState = filterState.copy(selectedCategoryText = newCategory)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    listOf("Chinese", "Local Dish", "Fruit", "BreakFast").forEach { text ->
                        FilterButton(
                            text = text,
                            isSelected = text == filterState.selectedCategoryText,
                            onClick = {
                                val newCategory =
                                    if (text == filterState.selectedCategoryText) null else text
                                filterState = filterState.copy(selectedCategoryText = newCategory)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    listOf("Spanish", "Japanese", "Lunch").forEach { text ->
                        FilterButton(
                            text = text,
                            isSelected = text == filterState.selectedCategoryText,
                            onClick = {
                                val newCategory =
                                    if (text == filterState.selectedCategoryText) null else text
                                filterState = filterState.copy(selectedCategoryText = newCategory)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
            SmallButton(
                text = "Filter",
                modifier = Modifier
                    .padding(bottom = 22.dp)
                    .padding(horizontal = 100.dp)
            ) {
                onApplyFilter(filterState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun FilterSearchBottomSheetPreview() {
    FilterSearchBottomSheet(
        sheetState = rememberModalBottomSheetState(),
        currentFilterState = FilterSearchState()
    )

}