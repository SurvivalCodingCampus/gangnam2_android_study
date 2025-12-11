package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.FilterSearchState
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun FilterBottomSheet(
    filterSearchState: FilterSearchState,
    onFilterClick: (String, Int, String) -> Unit = fun(_, _, _) = Unit,
    modifier: Modifier = Modifier
) {
    var selectedTime by remember(filterSearchState.time) { mutableStateOf(filterSearchState.time) }
    var selectedRating by remember(filterSearchState.rate) { mutableStateOf(filterSearchState.rate) }
    var selectedCategory by remember(filterSearchState.category) { mutableStateOf(filterSearchState.category) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 30.dp)
            .padding(top = 31.dp, bottom = 22.dp)
    ) {
        Text(
            text = "Filter Search",
            style = AppTextStyles.smallTextRegular.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(28.dp))
        FilterSection(title = "Time") {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                listOf("All", "Newest", "Oldest", "Popularity").forEach { item ->
                    FilterButton(
                        value = item,
                        isSelected = selectedTime == item,
                        modifier = Modifier.clickable { selectedTime = item }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        FilterSection("Rate") {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                (5 downTo 1).forEach { rating ->
                    RatingButton(
                        rating = rating,
                        isSelected = selectedRating == rating,
                        modifier = Modifier.clickable { selectedRating = rating }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        FilterSection("Category") {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                listOf(
                    "All", "Cereal", "Vegetables", "Dinner",
                    "Chinese", "Local Dish", "Fruit", "BreakFast",
                    "Spanish", "Lunch"
                ).forEach { item ->
                    FilterButton(
                        value = item,
                        isSelected = selectedCategory == item,
                        modifier = Modifier.clickable { selectedCategory = item }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            SmallButton(
                text = "Filter",
                modifier = Modifier,
                onClick = {
                    onFilterClick(selectedTime, selectedRating, selectedCategory)
                }
            )
        }
    }
}

@Composable
fun FilterSection(title: String, layout: @Composable () -> Unit = {}) {
    Column {
        Text(title, style = AppTextStyles.smallTextRegular.copy(fontWeight = FontWeight.SemiBold))
        Spacer(modifier = Modifier.height(10.dp))
        layout()
    }
}

@Preview(showBackground = true)
@Composable
fun FilterBottomSheetPreview() {
    FilterBottomSheet(filterSearchState = FilterSearchState())
}

@Preview(showBackground = true)
@Composable
fun FilterSectionPreview() {
    var selectedTime by remember { mutableStateOf("Newest") }

    FilterSection("Title") {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf("All", "Newest", "Oldest", "Popularity").forEach { item ->
                FilterButton(
                    value = item,
                    isSelected = selectedTime == item,
                    modifier = Modifier.clickable { selectedTime = item }
                )
            }
        }
    }
}