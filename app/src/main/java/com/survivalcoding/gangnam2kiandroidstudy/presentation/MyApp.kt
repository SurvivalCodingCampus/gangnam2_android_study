package com.survivalcoding.gangnam2kiandroidstudy.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.NavigationRoot
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    deepLinkRoute: Route? = null,
) {
    NavigationRoot(
        modifier = modifier,
        deepLinkRoute = deepLinkRoute,
    )
}