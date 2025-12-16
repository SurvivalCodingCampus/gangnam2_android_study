package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    currentRoute: NavKey,
    onItemClick: (Route) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
            .height(58.dp)
            .padding(10.dp, 10.dp, 10.dp, 24.dp),
        containerColor = AppColors.white,
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            val selected = currentRoute::class == item.route::class

            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(
                            if (selected) item.selectedIcon else item.unselectedIcon
                        ),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Unspecified,
                    unselectedIconColor = AppColors.gray4,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BottomNavBarPreview() {
    BottomNavBar(
        items = listOf(
            BottomNavItem(
                route = Route.Home,
                selectedIcon = R.drawable.ic_home_nav,
                unselectedIcon = R.drawable.ic_home_outline,
                label = "Home"
            ),
            BottomNavItem(
                route = Route.SavedRecipes,
                selectedIcon = R.drawable.ic_bookmark_nav,
                unselectedIcon = R.drawable.ic_bookmark_outline,
                label = "Saved"
            ),
            BottomNavItem(
                route = Route.Notifications,
                selectedIcon = R.drawable.ic_notification_bing_nav,
                unselectedIcon = R.drawable.ic_notification_bing_outline,
                label = "Notifications"
            ),
            BottomNavItem(
                route = Route.Profile,
                selectedIcon = R.drawable.ic_profile_nav,
                unselectedIcon = R.drawable.ic_profile_outline,
                label = "Profile"
            )
        ),
        Route.Home,
        onItemClick = {}
    )
}
