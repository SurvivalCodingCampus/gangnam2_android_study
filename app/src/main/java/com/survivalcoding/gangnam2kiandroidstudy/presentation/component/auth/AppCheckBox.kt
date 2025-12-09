package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun AppCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(17.dp)
            .background(
                color = if (checked) AppColors.secondary100 else Color.Transparent,
                shape = RoundedCornerShape(5.dp),
            )
            .border(
                width = 1.dp,
                color = AppColors.secondary100,
                shape = RoundedCornerShape(5.dp),
            )
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_checked),
                contentDescription = if (checked)
                    stringResource(R.string.ic_check_description)
                else
                    stringResource(R.string.ic_uncheck_description),
                tint = AppColors.white,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppCheckBoxPreview() {
    var checked by remember { mutableStateOf(false) }

    AppCheckBox(
        checked = checked,
        onCheckedChange = { checked = it }
    )
}