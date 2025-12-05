package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    leftTab: String,
    centerTab: String,
    rightTab: String,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(58.dp)
            .padding(top = 12.dp, bottom = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // left
        TabItem(
            text = leftTab,
            selected = selectedIndex == 0,
            onClick = { onTabSelected(0) },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        )

        Spacer(Modifier.width(7.dp))

        // center
        TabItem(
            text = centerTab,
            selected = selectedIndex == 1,
            onClick = { onTabSelected(1) },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        )

        Spacer(Modifier.width(7.dp))

        // right
        TabItem(
            text = rightTab,
            selected = selectedIndex == 2,
            height = 33.dp,
            onClick = { onTabSelected(2) },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
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
            leftTab = "Left",
            centerTab = "Center",
            rightTab = "Right",
            selectedIndex = selectedTab1,
            onTabSelected = {
                selectedTab1 = it
            }
        )

        TripleTabBar(
            leftTab = "Left",
            centerTab = "Center",
            rightTab = "Right",
            selectedIndex = selectedTab2,
            onTabSelected = {
                selectedTab2 = it
            }
        )

        TripleTabBar(
            leftTab = "Left",
            centerTab = "Center",
            rightTab = "Right",
            selectedIndex = selectedTab3,
            onTabSelected = {
                selectedTab3 = it
            }
        )
    }
}
