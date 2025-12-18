package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            OutlinedTextField(
                value = query,
                modifier = Modifier
                    .fillMaxWidth() // Box가 weight를 가지므로 fillMaxWidth로 채웁니다.
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = AppColors.white),
                placeholder = {
                    Text(
                        text = "Search recipe",
                        color = AppColors.gray4,
                        style = AppTextStyles.smallerTextRegular
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.gray4,
                    unfocusedBorderColor = AppColors.gray4,
                ),
                shape = RoundedCornerShape(10.dp),
                onValueChange = onQueryChange,

                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.outline_search_normal),
                        contentDescription = "검색 아이콘",
                        modifier = Modifier.size(20.dp),
                        tint = AppColors.gray4,
                    )
                },

                // 읽기 전용으로 만들어 키보드가 올라오지 않게 합니다.
                readOnly = true,
                // 터치 이벤트를 막아 하위 Box가 이벤트를 받을 수 있게 합니다.
                enabled = false,
            )

            // OutlinedTextField 위에 투명한 Box를 덮어 클릭 이벤트를 가로챕니다.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick() }
            )
        }

        Spacer(Modifier.width(20.dp))

        // 필터 아이콘
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = AppColors.primary100)
                .clickable { onFilterClick() }
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_setting_4),
                contentDescription = "필터 아이콘",
                tint = AppColors.white,
                modifier = Modifier
                    .padding(10.dp)
                    .size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar(
        query = "",
        onQueryChange = {},
        onFilterClick = {},
    )
}
