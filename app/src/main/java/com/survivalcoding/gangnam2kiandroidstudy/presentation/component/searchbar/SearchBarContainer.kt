package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.searchbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R

@Composable
fun SearchBarContainer(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onFilterClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        SearchBar(
            modifier = Modifier.weight(1f),
            value = value,
            placeholderText = stringResource(R.string.search_bar_placeholder),
            onValueChange = onValueChange
        )

        Spacer(Modifier.width(20.dp))

        SearchFilterButton(onClick = onFilterClick)
    }

}

@Preview(showBackground = true)
@Composable
private fun SearchBarContainerPreview() {
    // SearchBarContainer()
}