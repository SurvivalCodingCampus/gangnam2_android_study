package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Search
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SettingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SearchRecipesScreen(viewModel: SearchRecipesViewModel = viewModel(factory = SearchRecipesViewModel.Factory)) {
    val searchRecipes by viewModel.state.collectAsState(SearchRecipesState())
    val showBottomSheet = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(54.dp))
        Text(
            "Search recipes",
            modifier = Modifier.fillMaxWidth(),
            style = AppTextStyles.mediumTextBold.copy(fontSize = 18.sp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(17.dp))
        Row {
            Box(modifier = Modifier.weight(1f)) {
                Search(onValueChange = {
                    viewModel.changeSearchInputText(it)


                })
            }
            Spacer(modifier = Modifier.width(20.dp))
            SettingButton {
                showBottomSheet.value = true
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = if (searchRecipes.searchInputText.isBlank()) "Recent recipes" else "Search recipes",
                style = AppTextStyles.normalTextBold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
            Text(
//나중에 stateflow를 collect해서 수정
                text = if (searchRecipes.resultRecipes.isNotEmpty()) "${searchRecipes.resultRecipes.size} results" else "0 result",
                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3),
                textAlign = TextAlign.End,
                modifier = Modifier,
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(searchRecipes.resultRecipes) { item ->
                SmallRecipeCard(item)
            }
        }

    }

    if (showBottomSheet.value) {
        FilterBottomSheet(
            time = searchRecipes.selectedTime,
            rate = searchRecipes.selectedRate,
            category = searchRecipes.selectedCategory,
            onDismiss = { time, rate, category ->
                showBottomSheet.value = false
                Log.d("SearchRecipesScreen", "time: $time, rate: $rate, category: $category")
                viewModel.filterRecipes(time ?: "", rate ?: "", category ?: "")
            })
    }

}

@Preview(showBackground = true)
@Composable
fun SearchRecipesScreenPreview() {
    SearchRecipesScreen()
}