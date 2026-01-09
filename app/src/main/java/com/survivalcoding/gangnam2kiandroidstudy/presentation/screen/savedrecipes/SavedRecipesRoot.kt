package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import android.content.Intent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.savedrecipes.SavedRecipesActivity
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesRoot(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: SavedRecipesViewModel = koinViewModel(),
    onNavigate: (SavedRecipesNavigation) -> Unit = {},
    onActivityLaunched: () -> Unit,
) {
    val context = LocalContext.current

    /*
    레거시용 SavedRecipesActivity 로 이동
     */
    LaunchedEffect(Unit) {
        val intent = Intent(context, SavedRecipesActivity::class.java)
        context.startActivity(intent)
        onActivityLaunched()
    }

//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//    val lazyListState = rememberLazyListState()
//
//    LaunchedEffect(viewModel.event) {
//        viewModel.event.collect { event ->
//            when (event) {
//                is SavedRecipesEvent.NavigateToDetails -> onNavigate(
//                    SavedRecipesNavigation.RecipeDetails(event.recipeId),
//                )
//            }
//        }
//    }
//
//    LaunchedEffect(lazyListState) {
//        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
//            .distinctUntilChanged()
//            .collect {
//                if (it != null && uiState.recipes.size >= 5 && it == uiState.recipes.size) {
//                    snackbarHostState.showSnackbar("마지막 데이터입니다.")
//                }
//            }
//    }
//
//    SavedRecipesScreen(
//        modifier = modifier,
//        lazyListState = lazyListState,
//        uiState = uiState,
//        onAction = viewModel::onAction,
//    )
}