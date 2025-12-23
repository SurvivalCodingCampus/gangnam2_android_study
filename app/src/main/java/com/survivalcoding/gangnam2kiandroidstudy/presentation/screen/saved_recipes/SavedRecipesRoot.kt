package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesRoot(
    viewmodel: SavedRecipesViewModel = koinViewModel(),
    onRecipeClick: (Recipe) -> Unit = {}
) {
    val state = viewmodel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Scaffold(
        snackbarHost = {
            SnackbarHost(snackBarHostState) {
                Snackbar() {
                    Text(text = it.visuals.message)
                }
            }
        }
    ) {
        SavedRecipesScreen(
            state = state.value, onBookMarkClick = {
                viewmodel.delete(it)
            }, onRecipeClick = onRecipeClick,
            onReachEnd = {
                scope.launch {
                    snackBarHostState.showSnackbar("끝에 도달했습니다.")
                }
            },

        )
    }


}


@Preview(showBackground = true)
@Composable
private fun SavedRecipesRootPreview() {
    //SavedRecipesRoot()
}