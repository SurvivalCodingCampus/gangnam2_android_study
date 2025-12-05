package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import androidx.compose.ui.draw.clip // clip import 추가

@Composable
fun IngredientItem(imageUrl: String, foodName: String, foodGram: Int) {
    Row(modifier = Modifier.height(76.dp)) {
        Spacer(modifier = Modifier.width(30.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(color = AppColors.gray4, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center

        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(15.dp))
                AsyncImage(
                    contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    model = imageUrl,
                    contentDescription = "음식"
                )
                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = foodName,
                    modifier = Modifier.weight(1f), style = AppTextStyles.normalTextBold
                )

                Text(
                    "${foodGram}g",
                    modifier = Modifier,
                    style = AppTextStyles.smallTextRegular.copy(color = AppColors.gray3)
                )
                Spacer(modifier = Modifier.width(15.dp))

            }
        }
        Spacer(modifier = Modifier.width(30.dp))


    }

}

@Preview(showBackground = true)
@Composable
fun IngredientItemPreview() {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {

        IngredientItem(
            "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
            "Tomatos",
            500
        )
    }


}