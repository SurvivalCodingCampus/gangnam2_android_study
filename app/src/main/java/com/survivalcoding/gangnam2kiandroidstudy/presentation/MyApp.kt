package com.survivalcoding.gangnam2kiandroidstudy.presentation

import androidx.compose.runtime.Composable
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.NavigationRoot
import kotlinx.coroutines.flow.Flow

@Composable
fun MyApp(deepLinkFlow: Flow<String>) {
    NavigationRoot(deepLinkFlow = deepLinkFlow)
}
