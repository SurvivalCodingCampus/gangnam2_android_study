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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.dialog.MoreDropdownMenu
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun CustomAppTopBar(
    modifier: Modifier = Modifier,
    text: String = "",
    showBackButton: Boolean = false,
    showMoreButton: Boolean = false,
    onBackClick: () -> Unit = {},
    isMenuExpanded: Boolean = false,
    onMoreClick: () -> Unit = {},
    onMenuDismiss: () -> Unit = {},
    onMoreAction: (MoreAction) -> Unit = {}

) {
    Row(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .height(27.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (showBackButton) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onBackClick)
            )
        } else {
            Spacer(modifier = Modifier.size(24.dp))
        }

        Text(
            text = text,
            style = AppTextStyles.mediumTextBold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        if (showMoreButton) {
            Box {
                Icon(
                    painter = painterResource(R.drawable.ic_more_outline),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onMoreClick() } // 클릭 시 상태 변경 호출
                )

                // MoreDropdownMenu를 연결
                MoreDropdownMenu(
                    expanded = isMenuExpanded,
                    onDismiss = onMenuDismiss,
                    onShareClick = { onMoreAction(MoreAction.Share) },
                    onRateClick = { onMoreAction(MoreAction.Rate) },
                    onReviewClick = { onMoreAction(MoreAction.Review) },
                    onUnsaveClick = { onMoreAction(MoreAction.Unsave) }
                )
            }
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