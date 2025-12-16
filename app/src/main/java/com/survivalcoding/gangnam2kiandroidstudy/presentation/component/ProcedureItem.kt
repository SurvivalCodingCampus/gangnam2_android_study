package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun ProcedureItem(
    procedure: Procedure,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .background(color = AppColors.gray4, shape = RoundedCornerShape(12.dp))
    ) {
        Spacer(Modifier.height(10.dp))

        Column(modifier = Modifier.padding(horizontal = 15.dp)) {
            Text(
                text = "Step ${procedure.step}",
                style = AppTextStyles.smallerTextBold.copy(fontWeight = FontWeight.SemiBold),
                color = AppColors.labelColour
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = procedure.content,
                style = AppTextStyles.smallerTextRegular.copy(lineHeight = 16.sp),
                color = AppColors.gray3
            )
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProcedureItemPreview() {
    val procedure = Procedure(
        step = 1,
        content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
        recipeId = 1
    )

    ProcedureItem(
        procedure = procedure,
        modifier = Modifier.padding(30.dp)
    )
}