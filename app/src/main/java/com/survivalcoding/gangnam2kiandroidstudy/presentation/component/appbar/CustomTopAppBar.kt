package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun CustomAppTopBar(
    modifier: Modifier = Modifier,
    text: String = "",
    showBackButton: Boolean = false,
    showMoreButton: Boolean = false,
    onBackClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(top = 10.dp)
            .height(27.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Back Icon
        if (showBackButton) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = stringResource(R.string.ic_back_description),
                tint = AppColors.black,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() }
            )
        } else {
            Spacer(modifier = Modifier.size(24.dp))
        }

        // Title
        Text(
            text = text,
            style = AppTextStyles.mediumTextBold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        // More Icon
        if (showMoreButton) {
            Icon(
                painter = painterResource(R.drawable.ic_more_outline),
                contentDescription = stringResource(R.string.ic_more_description),
                tint = AppColors.black,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(){ onMoreClick() }
            )
        } else {
            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CustomAppTopBarPreview() {
    CustomAppTopBar(
        text = "Saved recipes",
        showBackButton = true,
    )
}