package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    currentRoute: NavKey?,

    onItemClick: (Route) -> Unit,
) {

    val colors = NavigationBarItemDefaults.colors(
        selectedIconColor = AppColors.primary100,
        unselectedIconColor = AppColors.gray4,
        indicatorColor = Transparent,
    )

    NavigationBar(
        modifier = modifier,
        containerColor = AppColors.white,
        tonalElevation = 6.dp,
    ) {
        NavigationBarItem(
            selected = currentRoute is Route.Home,
            onClick = { onItemClick(Route.Home) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.outline_home),
                    contentDescription = "홈 아이콘",
                )
            },
            colors = colors,
        )

        NavigationBarItem(
            selected = currentRoute is Route.SavedRecipes,
            onClick = { onItemClick(Route.SavedRecipes) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.outline_bookmark_inactive),
                    contentDescription = "북마크 아이콘",
                )
            },
            colors = colors,
        )

        NavigationBarItem(
            selected = currentRoute is Route.Notifications,
            onClick = { onItemClick(Route.Notifications) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.outline_notification_bing),
                    contentDescription = "알림 아이콘",
                )
            },
            colors = colors,
        )

        NavigationBarItem(
            selected = currentRoute is Route.Profile,
            onClick = { onItemClick(Route.Profile) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.outline_profile),
                    contentDescription = "프로필 아이콘",
                )
            },
            colors = colors,
        )
    }
}

@Preview
@Composable
private fun PreviewNavigationBar() {
    val currentRoute = Route.SavedRecipes
    NavigationBar(
        currentRoute = currentRoute,
        onItemClick = {},
    )
}