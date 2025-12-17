package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun RecipeCard(
    recipe: Recipe,
    isSaved: Boolean,
    onBookMarkClick: () -> Unit,
    onRecipeClick: (Recipe) -> Unit = {},
) {
    Row(modifier = Modifier.height(150.dp)) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = { onRecipeClick(recipe) }),

            ) { // Main Box, clip 추가
            // 배경 이미지
            AsyncImage(
                model = recipe.image,
                contentDescription = "레시피 배경",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(modifier = Modifier.fillMaxSize()) { // 콘텐츠 Column, Box를 가득 채움
                Spacer(modifier = Modifier.height(10.dp)) // 상단 여백
                Row { // 이 Row는 별점 Box를 감싸는 역할만 함
                    Spacer(modifier = Modifier.width(10.dp)) // 왼쪽 여백을 위해 Row 내부에 Spacer 추가

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp), // 이 Box에 오른쪽 여백 추가
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
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom // 하단 정렬
                    ) {
                        Text(
                            recipe.name.ifEmpty { "" },
                            modifier = Modifier.width(200.dp),
                            style = AppTextStyles.smallTextBold.copy(color = AppColors.white)
                        )
                        Text(
                            if(recipe.chef.isNotEmpty()) "By ${recipe.chef}" else "",
                            modifier = Modifier,
                            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.white)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }

            // 오른쪽 하단 텍스트 및 원 추가
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                Row(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) { // 여기에 bottom padding 추가
                    Image(
                        painter = painterResource(R.drawable.timer),
                        contentDescription = "알람"
                    )
                    Spacer(modifier = Modifier.width(5.dp)) // 이미지와 텍스트 사이 간격
                    Text(
                        text = recipe.time,
                        modifier = Modifier.width(38.dp), // 필요한 경우 너비 조절
                        style = AppTextStyles.smallerTextRegular.copy(color = AppColors.white) // 스타일 지정
                    )
                    Spacer(modifier = Modifier.width(10.dp)) // 텍스트와 원 사이 간격
                    Box(
                        modifier = Modifier
                            .size(24.dp) // 24dp 크기
                            .clip(CircleShape) // 원형으로 자르기
                            .background(Color.White)
                            .clickable(onClick = { onBookMarkClick() }),
                        contentAlignment = Alignment.Center,

                        ) {
                        Image(
                            painter = if (isSaved) painterResource(R.drawable.inactive) else painterResource(
                                R.drawable.outline_bookmark_inactive
                            ),
                            contentDescription = ""
                        )


                    }
                    Spacer(modifier = Modifier.width(10.dp)) // 여기에 오른쪽 여백을 추가
                }
            }
        }

    }
    Spacer(modifier = Modifier.height(20.dp)) // 여기에 오른쪽 여백을 추가

}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
//    val recipeDto = Recipe(
//        category = "Test",
//        chef = "Chef John",
//        id = 1,
//        image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
//        name = "Grilled Steak",
//        rating = 4.5,
//        time = "30 min" // 예시 시간 추가
//    )
//    RecipeCard(recipeDto,true,{})
}