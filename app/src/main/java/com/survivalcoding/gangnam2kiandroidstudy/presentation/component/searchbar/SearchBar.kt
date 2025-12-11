package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.searchbar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    placeholderText: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by rememberSaveable { mutableStateOf(false) }

    // 포커스 변경을 감지
    val focusModifier = Modifier.onFocusChanged { isFocused = it.isFocused }

    val borderColor = if (isFocused) AppColors.primary100 else AppColors.gray4

    Box(
        modifier = modifier
            .height(40.dp)
            .border(
                width = 1.3.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 11.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(R.drawable.ic_search_normal_outline),
                tint = borderColor,
                contentDescription = null
            )

            Spacer(Modifier.width(10.dp))

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = focusModifier.weight(1f),
                singleLine = true,
                textStyle = AppTextStyles.smallerTextRegular.copy(
                    color = AppColors.black
                ),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholderText,
                            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray4),
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar(
        value = "",
        placeholderText = "Search Recipe",
        onValueChange = {}
    )
}