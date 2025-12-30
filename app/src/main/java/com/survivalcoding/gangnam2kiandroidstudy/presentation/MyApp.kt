package com.survivalcoding.gangnam2kiandroidstudy.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.NavigationRoot

@Composable
fun MyApp(
    deepLinkUri: String? = null,
) {
    NavigationRoot(deepLinkUri = deepLinkUri)
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApp()
}

