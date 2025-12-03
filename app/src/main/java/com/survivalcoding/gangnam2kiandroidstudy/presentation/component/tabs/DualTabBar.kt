package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DualTabBar(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.size(width = 375.dp, height = 58.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Left
        TabItem(
            text = "Label",
            selected = selectedIndex == 0,
            onClick = { onTabSelected(0) }
        )

        Spacer(Modifier.size(15.dp))

        // Right
        TabItem(
            text = "Label",
            selected = selectedIndex == 1,
            onClick = { onTabSelected(1) }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DualTabBarPreview() {
    var selectedTab by remember { mutableStateOf(0) }

    DualTabBar(
        selectedIndex = selectedTab,
        onTabSelected = {
            selectedTab = it
        }
    )
}
