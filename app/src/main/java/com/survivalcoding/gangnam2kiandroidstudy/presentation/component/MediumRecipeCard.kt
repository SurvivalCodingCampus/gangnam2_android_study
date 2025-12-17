package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppTextStyles
import kotlinx.serialization.json.Json

@Composable
fun MediumRecipeCard(recipe: Recipe) {
    Box(
        modifier = Modifier
            .size(width = 150.dp, height = 231.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .height(176.dp)
                    .fillMaxWidth()
                    .background(
                        color = AppColors.gray4.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    Spacer(modifier = Modifier.height(66.dp))
                    Text(
                        recipe.name,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = AppTextStyles.smallTextBold.copy(
                            color = AppColors.gray1,
                            fontWeight = FontWeight.SemiBold
                        ),
                        minLines = 2,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(19.dp))
                    Text(
                        modifier = Modifier,
                        text = "Time",
                        style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        modifier = Modifier,
                        text = recipe.time,
                        style = AppTextStyles.smallerTextBold.copy(color = AppColors.gray1)
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 10.dp)
                        .size(24.dp)
                        .background(color = AppColors.white, shape = CircleShape)
                        .align(Alignment.BottomEnd)
                ) {
                    Image(
                        painter = painterResource(R.drawable.inactive),
                        contentDescription = "inactive",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        }
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(shape = CircleShape)
                .align(Alignment.TopCenter), contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = recipe.image,
                contentDescription = "레시피 이미지",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxSize(), contentScale = ContentScale.Crop
            )

        }



        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            Spacer(modifier = Modifier.height(39.dp))

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
                    text = recipe.rating.toString(), // 실제 레시피 평점 사용
                    style = AppTextStyles.smallerTextRegular.copy(
                        fontSize = 8.sp,
                        color = AppColors.black
                    ) // 색상 지정
                )
                Spacer(modifier = Modifier.width(7.dp))
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun MediumRecipeCardPreview() {
    val mock = """
        {"category": "Indian",
      "id": 1,
      "name": "Traditional spare ribs baked",
      "image": "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
      "chef": "Chef John",
      "time": "20 min",
      "rating": 4.0,
      "ingredients": [
        {
          "ingredient": {
            "id": 3,
            "name": "Pork",
            "image": "https://cdn.pixabay.com/photo/2019/12/20/14/44/meat-4708596_1280.jpg"
          },
          "amount": 500
        }]}"""
    val json = Json {
        ignoreUnknownKeys = true
    }
    MediumRecipeCard(json.decodeFromString(mock))

}