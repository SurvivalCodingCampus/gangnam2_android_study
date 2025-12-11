package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun HorizontalRecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    val imageBitmap = loadImageFromAssets("greek_salad.png")

    Box(
        modifier = modifier.size(width = 150.dp, height = 231.dp),
    ) {
        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 176.dp)
                .background(color = AppColors.gray4, shape = RoundedCornerShape(12.dp))
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Classic Greek Salad",
                modifier = Modifier.align(Alignment.Center),
                style = AppTextStyles.smallTextRegular.copy(
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = "Time",
                    style = AppTextStyles.smallerTextRegular
                        .copy(color = AppColors.gray3)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "15 Mins",
                    style = AppTextStyles.smallerTextRegular.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(color = AppColors.white, shape = CircleShape)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.bookmark),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center),
                    tint = AppColors.primary100
                )
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {

            // Top Recipe Image
            /*if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Recipe Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                // 로딩 실패 시 Placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .background(Color.LightGray)
                )
            }*/
            Image(
                painter = painterResource(R.drawable.circle_recipe),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.None
            )

            // Rating Badge
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 30.dp)
                    .background(AppColors.secondary20, RoundedCornerShape(20.dp))
                    .padding(horizontal = 7.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.star),
                    contentDescription = null,
                    tint = AppColors.rating
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = "4.5",
                    style = AppTextStyles.smallerTextRegular,
                    color = Color.Black
                )
            }
        }
    }
}


@Composable
fun loadImageFromAssets(fileName: String): ImageBitmap? {
    val context = LocalContext.current
    return remember(fileName) {
        try {
            val inputStream = context.assets.open(fileName)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            bitmap.asImageBitmap()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalRecipeCardPreview() {
    HorizontalRecipeCard(Recipe(0))
}