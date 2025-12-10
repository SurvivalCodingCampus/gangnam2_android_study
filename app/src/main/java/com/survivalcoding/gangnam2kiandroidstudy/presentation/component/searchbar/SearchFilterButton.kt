package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.searchbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun SearchFilterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
    ) {
    Box(
        modifier = modifier
            .size(40.dp)
            .background(color = AppColors.primary100, shape = RoundedCornerShape(10.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(R.drawable.ic_setting_4_outline),
            contentDescription = stringResource(R.string.ic_search_filter_description),
            tint = AppColors.white
        )

    }
}

@Preview
@Composable
private fun SearchFilterButtonPreview() {
    SearchFilterButton()
}