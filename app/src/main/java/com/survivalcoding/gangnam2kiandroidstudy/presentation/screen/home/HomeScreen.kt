package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Search
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SettingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton2
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun HomeScreen(
    state: HomeState,
    onViewmodelCalled: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(64.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column() {
                Text(
                    "Hello Jega",
                    modifier = Modifier.padding(start = 30.dp),
                    style = AppTextStyles.largeTextBold.copy(fontWeight = FontWeight.SemiBold)
                )//TODO나중에 이름 반영하게 수정
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    "What are you cooking today?",
                    modifier = Modifier.padding(start = 30.dp),
                    style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .padding(end = 30.dp)
                    .size(40.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = AppColors.secondary40)
                    .align(Alignment.CenterVertically), contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "프로필 사진",
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.padding(horizontal = 30.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                Search() {
                    //TODO 디자인 완성 후 콜백 구현
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            SettingButton {
                //TODO 디자인 완성 후 콜백 구현
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(start = 30.dp)
        ) {
            item {
                SmallButton2(text = "All", isSelected = state.selectedCategory == "All") {
                    onViewmodelCalled(it)
                }
            }
            item {
                SmallButton2(text = "Indian", isSelected = state.selectedCategory == "Indian") {
                    onViewmodelCalled(it)
                }
            }
            item {
                SmallButton2(text = "Italian", isSelected = state.selectedCategory == "Italian") {
                    onViewmodelCalled(it)
                }
            }
            item {
                SmallButton2(text = "Asian", isSelected = state.selectedCategory == "Asian") {
                    onViewmodelCalled(it)
                }
            }
            item {
                SmallButton2(text = "Chinese", isSelected = state.selectedCategory == "Chinese") {
                    onViewmodelCalled(it)
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(30.dp)
        ) {
            items(state.resultRecipes) { recipe ->
                MediumRecipeCard(recipe)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(state = HomeState(), onViewmodelCalled = { "All" })
}

