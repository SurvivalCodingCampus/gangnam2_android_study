package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun FilterButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    hasStar: Boolean = false,
) {
    val backgroundColor = if (isSelected) AppColors.primary100 else AppColors.white
    val textColor = if (isSelected) AppColors.white else AppColors.primary80
    val borderColor = if (isSelected) Color.Transparent else AppColors.primary80

    Row(
        modifier = modifier
            .height(28.dp)
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = AppTextStyles.smallerTextRegular.copy(
                color = textColor
            ),
            textAlign = TextAlign.Center
        )

        if (hasStar) {
            Spacer(Modifier.width(5.dp))
            Icon(
                painter = painterResource(R.drawable.ic_star_6),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun FilterButtonPreview() {
    var selected by rememberSaveable { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterButton(
            text = "All",
            isSelected = selected,
            onClick = { selected = !selected },
        )
    }
}


