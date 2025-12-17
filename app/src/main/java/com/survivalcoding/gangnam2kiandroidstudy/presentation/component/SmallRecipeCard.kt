package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppTextStyles

@Composable
fun SmallRecipeCard(recipe: Recipe) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        AsyncImage(
            model = recipe.image,
            contentDescription = "배경화면",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp))
        )
        Row(modifier = Modifier) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(brush = AppColors.searchRecipeScreenGradient)
                    .fillMaxHeight()
                    .padding(10.dp)

            ) { // Main Box, clip 추가
                // 배경 이미지
                Column(modifier = Modifier.fillMaxSize()) { // 콘텐츠 Column, Box를 가득 채움
                    Row { // 이 Row는 별점 Box를 감싸는 역할만 함
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) { // 별점 Box
                            Row(
                                modifier = Modifier
                                    .background(
                                        color = AppColors.secondary20,
                                        shape = RoundedCornerShape(20.dp)
                                    )
                                    .size(width = 37.dp, height = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Spacer(modifier = Modifier.width(7.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = "별점",
                                    modifier = Modifier.size(width = 8.dp, height = 8.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "${recipe.rating}", // 실제 레시피 평점 사용
                                    style = AppTextStyles.smallerTextRegular.copy(
                                        fontSize = 8.sp,
                                        color = AppColors.black
                                    ) // 색상 지정
                                )
                                Spacer(modifier = Modifier.width(7.dp))
                            }
                        }
                    }

                    // 왼쪽 하단 텍스트 (레시피 이름, 셰프 이름)
                    Row(modifier = Modifier.fillMaxSize()) { // 전체 공간 채우고 내부 Row로 정렬
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Bottom // 하단 정렬
                        ) {
                            Text(
                                text = recipe.name, // 실제 레시피 이름 사용
                                modifier = Modifier,
                                style = AppTextStyles.smallerTextBold.copy(color = AppColors.white)
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                "By ${recipe.chef}", // 실제 셰프 이름 사용
                                modifier = Modifier,
                                style = AppTextStyles.smallerTextRegular.copy(
                                    color = AppColors.gray3,
                                    fontSize = 8.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SmallRecipeCardPreview() {
//    val recipeDto = Recipe(
//        category = "Test",
//        chef = "Chef John",
//        id = 1,
//        image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
//        name = "Grilled Steak",
//        rating = 4.5,
//        time = "30 min" // 예시 시간 추가
//    )
//    SmallRecipeCard(recipeDto)
}