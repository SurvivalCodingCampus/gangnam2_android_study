package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun IngredientItem(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String,
    amount: Int,
) {

    Row(
        modifier = modifier
            .height(76.dp)
            .background(AppColors.gray4, RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp, horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 이미지
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(10.dp)),
            placeholder = painterResource(R.drawable.ic_more), // 로딩 중
            error = painterResource(R.drawable.ic_more), // 실패 시
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.width(16.dp))

        // 이름
        Text(
            text = name,
            style = AppTextStyles.normalTextBold.copy(color = AppColors.labelColor)
        )

        Spacer(modifier = Modifier.weight(1f))

        // 양
        Text(
            text = "${amount}g",
            style = AppTextStyles.smallTextRegular.copy(color = AppColors.gray3)
        )
    }
}


@Preview
@Composable
private fun IngredientItemPreview() {
    IngredientItem(
        name = "Tomatos",
        imageUrl = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
        amount = 500
    )

}