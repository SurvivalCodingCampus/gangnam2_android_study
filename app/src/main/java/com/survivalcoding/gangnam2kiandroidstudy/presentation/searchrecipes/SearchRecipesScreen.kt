package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.AppBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.GridRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SearchRecipesScreen() {
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
                    Column {
                        Row(
                            modifier = Modifier.padding(top = 7.dp).fillMaxWidth().height(40.dp)
                        ) {
                            SearchInputField(
                                value = "",
                                modifier = Modifier.weight(1f),
                                onValueChange = {},
                                placeholder = ""
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(
                                onClick = {},
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
                                text = "Search Result",
                                style = AppTextStyles.normalTextRegular.copy(fontWeight = FontWeight.SemiBold)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "255 results",
                                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                            )
                        }
                    }
                }
                items((0..50).toList()) {
                    val recipe = Recipe(
                        0,
                        "",
                        "Traditional spare ribs baked",
                        "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                        "Chef John",
                        "20 min",
                        4.0
                    )

                    GridRecipeCard(recipe)
                }
            }
            /*Row(
                modifier = Modifier.padding(top = 7.dp).fillMaxWidth().height(40.dp).background(Color.Red)
            ) {
                SearchInputField(value = "", onValueChange = {}, placeholder = "")
            }*/
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchRecipesScreenPreview() {
    SearchRecipesScreen()
}