package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.CategoryFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.RateFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.TimeFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.FilterButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.RatingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSearchBottomSheet(
    modifier: Modifier = Modifier,
    initialState: FilterSearchState,
    onDismiss: () -> Unit,
    onApplyFilter: (FilterSearchState) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var filterState by remember { mutableStateOf(initialState) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier,
        containerColor = AppColors.white,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
        dragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title
            Text(
                text = stringResource(R.string.bottom_sheet_title),
                style = AppTextStyles.smallTextBold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(20.dp))

            // TIME SECTION
            SectionTitle("Time")
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TimeFilter.values().forEach { option ->
                    FilterButton(
                        modifier = modifier.padding(end = 10.dp),
                        text = option.label,
                        isSelected = filterState.time == option,
                        onClick = { filterState = filterState.copy(time = option) }
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // RATE SECTION
            SectionTitle("Rate")
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                RateFilter.values().forEach { option ->
                    RatingButton(
                        rate = option.label,
                        isSelected = filterState.rate == option,
                        onClick = { filterState = filterState.copy(rate = option) }
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // CATEGORY SECTION
            SectionTitle("Category")
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                CategoryFilter.values().forEach { option ->
                    FilterButton(
                        modifier = modifier.padding(bottom = 10.dp, end = 10.dp),
                        text = option.label,
                        isSelected = filterState.category == option,
                        onClick = { filterState = filterState.copy(category = option) },
                        hasStar = option.hasStar,
                    )
                }
            }

            SmallButton(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(174.dp, 37.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.filter_button_text),
                onClick = { onApplyFilter(filterState) }
            )
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = AppTextStyles.smallTextBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        textAlign = TextAlign.Start
    )
}

@Preview(showBackground = true)
@Composable
fun FilterSearchBottomSheetPreview(modifier: Modifier = Modifier) {
    // FilterSearchBottomSheet()
}