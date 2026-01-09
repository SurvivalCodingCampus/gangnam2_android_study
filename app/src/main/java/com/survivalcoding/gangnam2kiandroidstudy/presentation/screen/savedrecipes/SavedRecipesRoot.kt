package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import android.app.Activity
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
    onActivityLaunched: () -> Unit = {},
) {
    val context = LocalContext.current

    /*
    레거시용 SavedRecipesActivity 로 이동
     */
    LaunchedEffect(Unit) {
        val intent = Intent(context, SavedRecipesActivity::class.java).apply {
            /*
            Activity Context가 아닌 곳에서 startActivity 호출 시 Task 생성이 필수이므로 플래그 추가
             */
            if (context !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching {
            context.startActivity(intent)
        }.onSuccess {
            onActivityLaunched()
        }
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