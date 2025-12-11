package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.di.DependencyContainer
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.AppBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.GridRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipesScreen(
    viewModel: SearchRecipesViewModel = viewModel(
        factory = DependencyContainer.provideSearchRecipesViewModelFactory(LocalContext.current)
    )
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
        AppBar(
            title = "Search recipes",
            navigationIcon = {
                Icon(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = "아이콘",
                    modifier = Modifier.size(20.dp)
                )
            }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 30.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                item(span = { GridItemSpan(2) }) {
                    GridHeader(
                        searchKeyword = uiState.searchKeyword,
                        onSearchKeywordChange = viewModel::onSearchKeywordChange,
                        onFilterButtonClick = { viewModel.onFilterButtonClick(true) },
                        filteredRecipesSize = uiState.filteredRecipes.size
                    )
                }
                if (uiState.searchKeyword.isEmpty()) {
                    items(uiState.allRecipes) { recipe ->
                        GridRecipeCard(recipe)
                    }
                } else {
                    items(uiState.filteredRecipes) { recipe ->
                        GridRecipeCard(recipe)
                    }
                }
            }
        }
        LaunchedEffect(Unit) {
            sheetState.expand()
        }
        if (uiState.isShowBottomSheet) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { viewModel.onFilterButtonClick(false) },
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
                containerColor = AppColors.white,
                dragHandle = null
            ) {
                FilterBottomSheet(
                    filterSearchState = uiState.filterSearchState,
                    onFilterClick = { time, rating, category ->
                        val filterResult = FilterSearchState(time, rating, category)

                        viewModel.onFilterComplete(filterResult)
                        // 필터 적용 처리
                        viewModel.onFilterButtonClick(false)
                    }
                )
            }
        }
    }
}

@Composable
fun GridHeader(
    searchKeyword: String,
    onSearchKeywordChange: (String) -> Unit,
    onFilterButtonClick: () -> Unit,
    filteredRecipesSize: Int = 0,
) {
    Column {
        Row(
            modifier = Modifier.padding(top = 7.dp).fillMaxWidth().height(40.dp)
        ) {
            SearchInputField(
                value = searchKeyword,
                modifier = Modifier.weight(1f),
                onValueChange = onSearchKeywordChange,
                placeholder = "Search recipe"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = onFilterButtonClick,
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonColors(
                    containerColor = AppColors.primary100,
                    contentColor = AppColors.white,
                    disabledContainerColor = AppColors.gray4,
                    disabledContentColor = AppColors.white
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.setting_4),
                    contentDescription = "필터 아이콘",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.padding(bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (!searchKeyword.isEmpty()) "Search Result" else "Recent Search",
                style = AppTextStyles.normalTextRegular.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (!searchKeyword.isEmpty()) {
                Text(
                    text = "$filteredRecipesSize results",
                    style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchRecipesScreenPreview() {
    SearchRecipesScreen()
}