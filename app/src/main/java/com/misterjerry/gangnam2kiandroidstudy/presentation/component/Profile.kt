package com.misterjerry.gangnam2kiandroidstudy.presentation.component

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.misterjerry.gangnam2kiandroidstudy.R
import com.misterjerry.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.misterjerry.gangnam2kiandroidstudy.presentation.ui.AppTextStyles

@Composable
fun Profile(name: String, region: String, imageUrl: String) {
    Row(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)

                .background(color = AppColors.black, shape = CircleShape)
        ) {
//            AsyncImage(//TODO 나중에 mock이미지 제거하고 async로 구현할 것
//                model = painterResource(R.drawable.profile),
//                contentDescription = "프로필 사진",
//                modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
//            )
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "프로필 사진",
                modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(name, style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold))
            Spacer(modifier = Modifier.height(2.dp))
            Row(modifier = Modifier) {
                Icon(
                    painter = painterResource(R.drawable.bold_location),
                    tint = AppColors.primary80,
                    contentDescription = "아이콘",
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(1.dp))
                Text(region, style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3))

            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .width(85.dp)
                .fillMaxHeight(), contentAlignment = Alignment.Center
        ) {
            SmallButton("Follow") { }
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    Profile("Laura wilson", "Lagos, Nigeria", "")

}