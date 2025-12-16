package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ingredient

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun ProcedureCard(
    step: Int,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = AppColors.gray4,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(vertical = 10.dp, horizontal = 15.dp)
    ) {
        Text(
            text = "Step $step",
            style = AppTextStyles.smallerTextBold
        )
        Spacer(Modifier.height(5.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = content,
            textAlign = TextAlign.Start,
            style = AppTextStyles.smallerTextRegular.copy(
                color = AppColors.gray3,
                lineHeight = 18.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProcedureCardPreview() {
    ProcedureCard(
        step = 1,
        content = "Lorem ipsum tempor incididunt ut labore et dolore, in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
    )
}
