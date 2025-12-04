package com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.data.model.Ad
import com.survivalcoding.gangnam2kiandroidstudy.R

@Composable
fun AdItem(
    ad: Ad,
    onClick: (Ad) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .clickable {
                onClick(ad)
            }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
            ) {
                Text(ad.title)
                Text(ad.description)
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier
                    .height(100.dp)
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.tesla),
                contentDescription = "telsa"
            )
        }
    }
}

@Preview
@Composable
private fun AdItemPreview() {
    val ad = Ad(
        title = "광고 제목",
        description = "광고 설명",
        imageUrl = "https://picsum.photos/200",
        linkUrl = "https://google.com"
    )

    AdItem(ad = ad)
}