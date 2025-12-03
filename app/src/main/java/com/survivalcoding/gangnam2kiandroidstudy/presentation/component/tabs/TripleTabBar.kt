package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun TripleTabBar(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.size(width = 375.dp, height = 58.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // left
        TabItem(
            text = "Label",
            selected = selectedIndex == 0,
            width = 107.dp,
            height = 33.dp,
            innerWidth = 83.dp,
            onClick = { onTabSelected(0) }
        )

        Spacer(Modifier.size(7.dp))

        // center
        TabItem(
            text = "Label",
            selected = selectedIndex == 1,
            width = 107.dp,
            height = 33.dp,
            innerWidth = 83.dp,
            onClick = { onTabSelected(1) }
        )

        Spacer(Modifier.size(7.dp))

        // right
        TabItem(
            text = "Label",
            selected = selectedIndex == 2,
            width = 107.dp,
            height = 33.dp,
            innerWidth = 83.dp,
            onClick = { onTabSelected(2) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TripleTabBarPreview() {
    var selectedTab1 by remember { mutableStateOf(0) }
    var selectedTab2 by remember { mutableStateOf(1) }
    var selectedTab3 by remember { mutableStateOf(2) }

    Column {
        TripleTabBar(
            selectedIndex = selectedTab1,
            onTabSelected = {
                selectedTab1 = it
            }
        )

        TripleTabBar(
            selectedIndex = selectedTab2,
            onTabSelected = {
                selectedTab2 = it
            }
        )

        TripleTabBar(
            selectedIndex = selectedTab3,
            onTabSelected = {
                selectedTab3 = it
            }
        )
    }
}
