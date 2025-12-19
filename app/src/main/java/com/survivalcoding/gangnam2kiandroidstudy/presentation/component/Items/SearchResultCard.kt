package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles


@Composable
fun SearchResultCard(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String,
    chef: String,
    rating: Double,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(150.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Box(
            modifier = modifier
                .size(150.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { onClick() }
        ) {
            // Background Image
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
            )

            // 반투명 오버레이
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0x80000000)),
                            startY = 120f
                        )
                    )
            )

            // Rating - 오른쪽 상단
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
                    .background(
                        AppColors.secondary20,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(vertical = 2.dp, horizontal = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_star_6),
                        contentDescription = "average rating",
                        tint = AppColors.secondary100,
                        modifier = Modifier.size(8.dp),
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        text = rating.toString(),
                        style = AppTextStyles.smallerTextRegular.copy(fontSize = 8.sp),
                    )
                }

            }

            // 아래 텍스트 영역
            Column (
                modifier = Modifier
                    .align (Alignment.BottomStart)
                    .padding(10.dp)

            ) {
                Text(
                    text = name,
                    style = AppTextStyles.smallerTextBold.copy(
                        color = AppColors.white
                    )
                )
                Text(
                    modifier = Modifier.padding(top =3.dp),
                    text = "By $chef",
                    style = AppTextStyles.smallerTextRegular.copy(
                        color = AppColors.white,
                        fontSize = 8.sp
                    )
                )

            }
        }


    }
}


@Preview(showBackground = true)
@Composable
private fun SearchResultCardPreview() {
    SearchResultCard(
        name = "Traditional spare ribs baked",
        imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        chef = "Chef John",
        rating = 4.0,
        onClick = {}
    )
}