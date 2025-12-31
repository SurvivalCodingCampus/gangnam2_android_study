package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun ProcedureItem(
    procedure: Procedure,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = AppColors.gray4, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        Text(
            text = "Step ${procedure.step}",
            style = AppTextStyles.smallerTextBold
        )
        Text(
            text = procedure.content,
            style = AppTextStyles.smallerTextRegular.copy(
                color = AppColors.gray3,
                lineHeight = 15.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProcedureItemPreview() {
    ProcedureItem(
        procedure = Procedure(
            recipeId = 1,
            step = 1,
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed"
        )
    )
}