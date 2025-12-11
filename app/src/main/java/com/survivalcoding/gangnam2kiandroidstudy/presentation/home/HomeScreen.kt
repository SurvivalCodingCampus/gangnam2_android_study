package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCategorySelector
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun HomeScreen() {
    var searchKeyword by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
        Spacer(modifier = Modifier.height(54.dp))
        Row(
            modifier = Modifier.padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.wrapContentWidth()) {
                Text(
                    text = "Hello Jega",
                    style = AppTextStyles.largeTextRegular.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 1
                )
                Text(
                    text = "What are you cooking today?",
                    modifier = Modifier.padding(top = 5.dp),
                    style = AppTextStyles.smallerTextRegular.copy(
                        fontWeight = FontWeight.Medium,
                        color = AppColors.gray3
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "아바타",
                modifier = Modifier.size(40.dp)
            )
        }
        Row(
            modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp).fillMaxWidth().height(40.dp)
        ) {
            SearchInputField(
                value = searchKeyword,
                modifier = Modifier.weight(1f),
                onValueChange = {},
                placeholder = "Search recipe"
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
        RecipeCategorySelector(
            items = listOf("All", "Indian", "Italian", "Asian", "Chinese", "Fruit", "Vegetables", "Protein", "Cereal", "Local Dishes"),
            modifier = Modifier.padding(top = 15.dp),
            contentPadding = PaddingValues(start = 30.dp),
            selectedCategory = "Indian",
            onCategoryClick = {  }
        )
        LazyRow(
            modifier = Modifier.padding(top = 15.dp),
            contentPadding = PaddingValues(horizontal = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items((0..5).toList()) {
                RecipeCard()
            }
        }
    }
}

@Composable
fun RecipeCard(
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
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    RecipeCard()
}