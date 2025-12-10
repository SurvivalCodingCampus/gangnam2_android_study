package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun AppBar(title: String, modifier: Modifier = Modifier, navigationIcon: @Composable () -> Unit = {}) {
    Box(
        modifier = modifier.padding(top = 44.dp).height(47.dp).fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth()) {
            navigationIcon()
            Spacer(modifier = Modifier.weight(1f))
        }
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            style = AppTextStyles.mediumTextRegular
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    AppBar("AppBar")
}