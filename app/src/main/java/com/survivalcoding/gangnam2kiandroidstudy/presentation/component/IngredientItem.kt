package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingrident
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun IngredientItem(
    ingrident: Ingrident,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(color = AppColors.gray4, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            val painter = if (LocalInspectionMode.current) {
                painterResource(R.drawable.tomato)
            } else {
                rememberAsyncImagePainter(ingrident.image)
            }

            Image(
                painter = painter,
                contentDescription = "${ingrident.name} image",
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(16.dp))

            Text(text = ingrident.name, modifier = Modifier.height(24.dp), style = AppTextStyles.normalTextBold)
            Spacer(Modifier.weight(1f))

            Text(
                text = ingrident.weight,
                modifier = Modifier.height(21.dp),
                style = AppTextStyles.smallTextRegular,
                color = AppColors.gray3
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IngredientItemPreview() {
    val ingrident = Ingrident(
        1,
        "Tomatos",
        "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
        "500g",
        1
    )
    Scaffold {
        IngredientItem(modifier = Modifier.padding(it), ingrident = ingrident)
    }
}
